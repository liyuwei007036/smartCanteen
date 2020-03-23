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
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.SummaryDTO;
import com.smart.canteen.dto.SummaryData;
import com.smart.canteen.dto.order.OrderSearch;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;
import com.smart.canteen.enums.OrderChannelEnum;
import com.smart.canteen.enums.OrderTypeEnum;
import com.smart.canteen.mapper.OrderMapper;
import com.smart.canteen.service.IOrderService;
import com.smart.canteen.vo.OrderVo;
import com.smart.canteen.vo.SummaryTotal;
import com.smart.canteen.vo.SummaryVO;
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
        Calendar now = Calendar.getInstance();
        Date end = now.getTime();
        now.add(Calendar.HOUR, -11);
        now.add(Calendar.MINUTE, -30);
        Date begin = now.getTime();
        List<String> strings = getBaseMapper().summaryOrderNum(begin, end, 30);
        Map<String, Long> res = new LinkedHashMap<>(16);

        while (end.after(begin)) {
            now.setTime(begin);
            int min = now.get(Calendar.MINUTE) / 30 * 30;
            now.set(Calendar.MINUTE, min);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM-dd HH:mm");
            res.put(key, 0L);
            now.add(Calendar.MINUTE, 30);
            begin = now.getTime();
        }

        strings.forEach(x -> res.put(x, ObjectUtil.getLong(res.get(x)) + 1));
        return res;
    }

    @Override
    public SummaryDTO getYearSaleData() {
        Calendar now = Calendar.getInstance();
        Date end = now.getTime();
        now.add(Calendar.YEAR, -1);
        now.add(Calendar.MONTH, 1);
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date begin = now.getTime();
        Map<String, Double> res = new LinkedHashMap<>(16);
        List<Map<String, Object>> maps = getBaseMapper().summaryYearSale(begin, end);
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM");
            res.put(key, 0d);
            now.add(Calendar.MONTH, 1);
            begin = now.getTime();
        }
        maps.forEach(x -> {
            String yearMonth = ObjectUtil.getString(x.get("yearMonth"));
            double money = ObjectUtil.getDouble(x.get("money"));
            res.put(yearMonth, MathUtil.add(ObjectUtil.getDouble(res.get(yearMonth)), money));
        });

        end = Calendar.getInstance().getTime();
        now.setTime(DateUtils.clearMilliTime(end));
        now.set(Calendar.MONTH, 0);
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date start = now.getTime();
        List<SummaryData> data = new LinkedList<>();
        res.forEach((key, value) -> data.add(new SummaryData(key, value)));
        SummaryTotal summaryTotal = getSaleSummary(start, end);
        return new SummaryDTO(data, summaryTotal.getTotal(), summaryTotal.getAvg());
    }


    @Override
    public SummaryDTO getMonthSaleData() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date end = now.getTime();
        now.add(Calendar.DAY_OF_MONTH, -12);
        Date begin = now.getTime();
        Map<String, Double> res = new LinkedHashMap<>(16);
        List<Map<String, Object>> maps = getBaseMapper().summaryMonthSale(begin, end);
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM-dd");
            res.put(key, 0d);
            now.add(Calendar.DAY_OF_MONTH, 1);
            begin = now.getTime();
        }
        maps.forEach(x -> {
            String yearMonth = ObjectUtil.getString(x.get("yearMonth"));
            double money = ObjectUtil.getDouble(x.get("money"));
            res.put(yearMonth, MathUtil.add(ObjectUtil.getDouble(res.get(yearMonth)), money));
        });
        now.setTime(end);
        now.set(Calendar.DAY_OF_MONTH, 1);
        Date start = now.getTime();
        List<SummaryData> data = new LinkedList<>();
        res.forEach((key, value) -> data.add(new SummaryData(key, value)));
        SummaryTotal summaryTotal = getSaleSummary(start, end);
        return new SummaryDTO(data, summaryTotal.getTotal(), summaryTotal.getAvg());
    }

    @Override
    public SummaryDTO getDaySaleData() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR_OF_DAY, 1);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        Date end = now.getTime();
        now.add(Calendar.HOUR_OF_DAY, -12);
        Date begin = now.getTime();
        Map<String, Double> res = new LinkedHashMap<>(16);
        List<Map<String, Object>> maps = getBaseMapper().summaryDaySale(begin, end);
        while (end.after(begin)) {
            now.setTime(begin);
            String key = DateUtils.dateToStr(now.getTime(), "yyyy-MM-dd-HH");
            res.put(key, 0d);
            now.add(Calendar.HOUR_OF_DAY, 1);
            begin = now.getTime();
        }
        maps.forEach(x -> {
            String yearMonth = ObjectUtil.getString(x.get("yearMonth"));
            double money = ObjectUtil.getDouble(x.get("money"));
            res.put(yearMonth, MathUtil.add(ObjectUtil.getDouble(res.get(yearMonth)), money));
        });
        now.setTime(end);
        now.set(Calendar.HOUR_OF_DAY, 0);
        Date start = now.getTime();
        List<SummaryData> data = new ArrayList<>();
        res.forEach((key, value) -> data.add(new SummaryData(key, value)));
        SummaryTotal summaryTotal = getSaleSummary(start, end);
        return new SummaryDTO(data, summaryTotal.getTotal(), summaryTotal.getAvg());
    }


    @Override
    public SummaryTotal getSaleSummary(Date start, Date end) {
        int dayDiff = Math.max(DateUtils.getDayDiff(end, start), 1);
        Double summary = getBaseMapper().getSummary(start, end);
        Double avg = MathUtil.div(ObjectUtil.getDouble(summary), dayDiff, 2);
        return new SummaryTotal(summary, avg);
    }

    @Override
    public SummaryVO getUpdateData() {
        SummaryVO summaryVO = new SummaryVO();
        summaryVO.setLine(getSummaryDay());
        summaryVO.setYear(getDaySaleData());
        summaryVO.setMonth(getMonthSaleData());
        summaryVO.setDay(getDaySaleData());
        return summaryVO;
    }
}
