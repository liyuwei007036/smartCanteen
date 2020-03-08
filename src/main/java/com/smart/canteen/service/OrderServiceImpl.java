package com.smart.canteen.service;

import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;
import com.smart.canteen.enums.OrderTypeEnum;
import com.smart.canteen.mapper.OrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Async
    @Override
    public boolean addOrderForMachine(IcCard card, Double money, String machineNo) {
        Order order = new Order();
        order.setCardId(card.getId());
        order.setBalance(card.getCurrentBalance());
        order.setCardNo(card.getNo());
        order.setEmployeeName(card.getEmployeeName());
        order.setEmployeeNo(card.getEmployeeNo());
        order.setMoney(money);
        order.setCreateTime(LocalDateTime.now());
        order.setType(OrderTypeEnum.MACHINE);
        order.setMachineNo(machineNo);
        return save(order);
    }
}
