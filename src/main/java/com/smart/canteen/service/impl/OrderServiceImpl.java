package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.core.dto.Account;
import com.lc.core.utils.ModelMapperUtils;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.order.OrderSearch;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;
import com.smart.canteen.enums.OrderChannelEnum;
import com.smart.canteen.enums.OrderTypeEnum;
import com.smart.canteen.mapper.OrderMapper;
import com.smart.canteen.service.IOrderService;
import com.smart.canteen.vo.OrderVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
        order.setCreateTime(LocalDateTime.now());
        order.setChannel(OrderChannelEnum.MACHINE);
        order.setMachineNo(machineNo);
        order.setType(OrderTypeEnum.NORMAL);
        return save(order);
    }


    @Override
    public boolean addOrderForDeduction(IcCard card, Double money, Account account, Double balance) {
        Order order = new Order();
        order.setCardId(card.getId());
        order.setBalance(balance);
        order.setCardNo(card.getNo());
        order.setEmployeeName(card.getEmployeeName());
        order.setEmployeeNo(card.getEmployeeNo());
        order.setMoney(money);
        order.setCreateTime(LocalDateTime.now());
        order.setChannel(OrderChannelEnum.DEDUCTION);
        order.setType(OrderTypeEnum.FILL_BUCKLE);
        order.setCreatorAccount(account.getAccount());
        order.setCreatorName(account.getName());
        order.setCreateTime(LocalDateTime.now());
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

}
