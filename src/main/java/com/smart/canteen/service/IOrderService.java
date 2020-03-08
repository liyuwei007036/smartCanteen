package com.smart.canteen.service;

import com.smart.canteen.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param order
     */
    void addOrder(Order order);
}
