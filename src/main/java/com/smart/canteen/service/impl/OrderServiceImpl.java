package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.annotations.Cache;
import com.lc.core.dto.Account;
import com.lc.core.utils.DateUtils;
import com.lc.core.utils.MathUtil;
import com.lc.core.utils.ModelMapperUtils;
import com.lc.core.utils.ObjectUtil;
import com.smart.canteen.dto.*;
import com.smart.canteen.dto.order.OrderSearch;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.enums.OrderChannelEnum;
import com.smart.canteen.enums.OrderTypeEnum;
import com.smart.canteen.mapper.OrderMapper;
import com.smart.canteen.service.IOrderService;
import com.smart.canteen.service.IRechargeLogService;
import com.smart.canteen.utils.DateUtil;
import com.smart.canteen.vo.OrderVo;
import com.smart.canteen.vo.SummaryTotal;
import com.smart.canteen.vo.SummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 充值记录 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private IRechargeLogService iRechargeLogService;

    @Override
    public boolean addOrderForMachine(IcCard card, Double money, String machineNo, Double balance) {
        Order order = new Order();
        order.setCardId(card.getId());
        order.setBalance(balance);
        order.setCardNo(card.getNo());
        order.setEmployeeName(card.getEmployeeName());
        order.setEmployeeNo(card.getEmployeeNo());
        order.setMoney(money);
        order.setCreateTime(new Date());
        order.setChannel(OrderChannelEnum.MACHINE);
        order.setMachineNo(machineNo);
        order.setType(OrderTypeEnum.NORMAL);
        order.setCreatorAccount(card.getEmployeeNo());
        order.setCreatorName(card.getEmployeeName());
        return save(order);
    }

    @Override
    public boolean addOrderForDeduction(IcCard card, Double money, Account account, Double balance, String desc) {
        Order order = new Order();
        order.setCardId(card.getId());
        order.setBalance(balance);
        order.setCardNo(card.getNo());
        order.setEmployeeName(card.getEmployeeName());
        order.setEmployeeNo(card.getEmployeeNo());
        order.setMoney(money);
        order.setCreateTime(new Date());
        order.setChannel(OrderChannelEnum.DEDUCTION);
        order.setType(OrderTypeEnum.FILL_BUCKLE);
        order.setCreatorAccount(account.getAccount());
        order.setCreatorName(account.getName());
        order.setDescription(desc);
        return save(order);
    }

    @Override
    public CommonList<OrderVo> listLogs(OrderSearch search) {
        Page<Order> voPage = new Page<>(search.getPage(), search.getSize());
        page(voPage, Wrappers.<Order>lambdaQuery()
                .eq(StringUtils.isNotEmpty(search.getCardNo()), Order::getCardNo, search.getCardNo())
                .eq(StringUtils.isNotEmpty(search.getEmpName()), Order::getEmployeeName, search.getEmpName())
                .eq(StringUtils.isNotEmpty(search.getEmpNo()), Order::getEmployeeNo, search.getEmpNo())
                .eq(search.getOrderType() != null, Order::getType, search.getOrderType())
                .eq(StringUtils.isNotEmpty(search.getOperatorName()), Order::getCreatorName, search.getOperatorName())
                .ge(search.getStart() != null, Order::getCreateTime, search.getStart())
                .lt(search.getEnd() != null, Order::getCreateTime, search.getEnd())
                .orderByDesc(Order::getCreateTime)
        );
        List<OrderVo> collect = voPage.getRecords().stream().map(x -> ModelMapperUtils.strict(x, OrderVo.class)).collect(Collectors.toList());
        return new CommonList<>(voPage.hasNext(), voPage.getTotal(), voPage.getCurrent(), collect);
    }

    @Override
    public Map<String, Long> getSummaryDay() {
        DateDTO dateDTO = DateUtil.getDay();
        Date begin = dateDTO.getStart();
        Date end = dateDTO.getEnd();
        Map<String, Long> res = new LinkedHashMap<>(16);
        OrderSummaryDTO orderTotal = getOrderTotal(begin, end);
        Calendar now = Calendar.getInstance();
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM-dd HH:00");
            res.put(key, 0L);
            now.add(Calendar.HOUR_OF_DAY, 1);
            begin = now.getTime();
        }
        orderTotal.getOrders().forEach(x -> {
            String yearMonth = ObjectUtil.getString(DateUtils.dateToStr(x.getCreateTime(), "yyyy-MM-dd HH:00"));
            res.put(yearMonth, ObjectUtil.getLong(res.get(yearMonth) + 1L));
        });
        return res;
    }

    @Override
    public SummaryDTO getYearSaleData() {
        DateDTO dateDTO = DateUtil.getYear();
        Date begin = dateDTO.getStart();
        Date end = dateDTO.getEnd();
        Map<String, Double> res = new LinkedHashMap<>(16);
        OrderSummaryDTO orderTotal = getOrderTotal(begin, end);
        Calendar now = Calendar.getInstance();
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM");
            res.put(key, 0d);
            now.add(Calendar.MONTH, 1);
            begin = now.getTime();
        }
        orderTotal.getOrders().forEach(x -> {
            String yearMonth = ObjectUtil.getString(DateUtils.dateToStr(x.getCreateTime(), "yyyy-MM"));
            double money = ObjectUtil.getDouble(x.getMoney());
            res.put(yearMonth, MathUtil.add(ObjectUtil.getDouble(res.get(yearMonth)), money));
        });
        dateDTO = DateUtil.getYear();
        begin = dateDTO.getStart();
        end = dateDTO.getEnd();
        List<SummaryData> data = new LinkedList<>();
        res.forEach((key, value) -> data.add(new SummaryData(key, value)));
        RechargeSummaryDTO rechargeTotal = iRechargeLogService.getRechargeTotal(begin, end);
        return new SummaryDTO(data, orderTotal.getTotal(), rechargeTotal.getNormal(), orderTotal.getFillBuckle(), rechargeTotal.getRefund(), orderTotal.getAvg());
    }

    @Override
    public SummaryDTO getMonthSaleData() {
        DateDTO dateDTO = DateUtil.getMonth();
        Date begin = dateDTO.getStart();
        Date end = dateDTO.getEnd();
        Map<String, Double> res = new LinkedHashMap<>(16);
        OrderSummaryDTO orderTotal = getOrderTotal(begin, end);
        List<Map<String, Object>> maps = getBaseMapper().summaryMonthSale(begin, end);
        Calendar now = Calendar.getInstance();
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM-dd");
            res.put(key, 0d);
            now.add(Calendar.DAY_OF_MONTH, 1);
            begin = now.getTime();
        }
        orderTotal.getOrders().forEach(x -> {
            String yearMonth = ObjectUtil.getString(DateUtils.dateToStr(x.getCreateTime(), "yyyy-MM-dd"));
            double money = ObjectUtil.getDouble(x.getMoney());
            res.put(yearMonth, MathUtil.add(ObjectUtil.getDouble(res.get(yearMonth)), money));
        });
        dateDTO = DateUtil.getMonth();
        begin = dateDTO.getStart();
        end = dateDTO.getEnd();
        List<SummaryData> data = new LinkedList<>();
        res.forEach((key, value) -> data.add(new SummaryData(key, value)));
        RechargeSummaryDTO rechargeTotal = iRechargeLogService.getRechargeTotal(begin, end);
        return new SummaryDTO(data, orderTotal.getTotal(), rechargeTotal.getNormal(), orderTotal.getFillBuckle(), rechargeTotal.getRefund(), orderTotal.getAvg());
    }

    @Override
    public SummaryDTO getDaySaleData() {
        DateDTO dateDTO = DateUtil.getDay();
        Date begin = dateDTO.getStart();
        Date end = dateDTO.getEnd();
        Map<String, Double> res = new LinkedHashMap<>(16);
        OrderSummaryDTO orderTotal = getOrderTotal(begin, end);
        List<Map<String, Object>> maps = getBaseMapper().summaryMonthSale(begin, end);
        Calendar now = Calendar.getInstance();
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM-dd-HH");
            res.put(key, 0d);
            now.add(Calendar.HOUR_OF_DAY, 1);
            begin = now.getTime();
        }
        orderTotal.getOrders().forEach(x -> {
            String yearMonth = ObjectUtil.getString(DateUtils.dateToStr(x.getCreateTime(), "yyyy-MM-dd-HH"));
            double money = ObjectUtil.getDouble(x.getMoney());
            res.put(yearMonth, MathUtil.add(ObjectUtil.getDouble(res.get(yearMonth)), money));
        });
        dateDTO = DateUtil.getDay();
        begin = dateDTO.getStart();
        end = dateDTO.getEnd();
        List<SummaryData> data = new LinkedList<>();
        res.forEach((key, value) -> data.add(new SummaryData(key, value)));
        RechargeSummaryDTO rechargeTotal = iRechargeLogService.getRechargeTotal(begin, end);
        return new SummaryDTO(data, orderTotal.getTotal(), rechargeTotal.getNormal(), orderTotal.getFillBuckle(), rechargeTotal.getRefund(), orderTotal.getAvg());
    }

    @Override
    public SummaryVO getUpdateData() {
        SummaryVO summaryVO = new SummaryVO();
        summaryVO.setLine(getSummaryDay());
        summaryVO.setYear(getYearSaleData());
        summaryVO.setMonth(getMonthSaleData());
        summaryVO.setDay(getDaySaleData());
        return summaryVO;
    }

    @Override
    public OrderSummaryDTO getOrderTotal(Date start, Date end) {
        List<Order> orders = list(Wrappers.<Order>lambdaQuery()
                .select(Order::getCreateTime, Order::getType, Order::getMoney)
                .ge(Order::getCreateTime, start)
                .le(Order::getCreateTime, end));
        Double fillBuckle = orders.parallelStream()
                .filter(x -> OrderTypeEnum.FILL_BUCKLE.equals(x.getType()))
                .map(Order::getMoney)
                .reduce((x, y) -> MathUtil.add(ObjectUtil.getDouble(x), ObjectUtil.getDouble(y)))
                .orElse(0d);
        Double normal = orders.parallelStream()
                .filter(x -> OrderTypeEnum.NORMAL.equals(x.getType()))
                .map(Order::getMoney)
                .reduce((x, y) -> MathUtil.add(ObjectUtil.getDouble(x), ObjectUtil.getDouble(y)))
                .orElse(0d);
        orders = orders.parallelStream().filter(x -> OrderTypeEnum.NORMAL.equals(x.getType())).collect(Collectors.toList());
        int dayDiff = Math.max(DateUtils.getDayDiff(end, start), 1);
        Double avg = MathUtil.div(ObjectUtil.getDouble(normal), dayDiff, 2);
        return new OrderSummaryDTO(normal, fillBuckle, orders, avg);
    }
}
