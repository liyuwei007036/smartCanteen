package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;

/**
 * <p>
 * 充值记录 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
public interface IOrderService extends IService<Order> {

    /**
     * 添加消费订单
     *
     * @param card
     * @param money
     * @param machineNo
     * @return
     */
    boolean addOrderForMachine(IcCard card, Double money, String machineNo);



}
