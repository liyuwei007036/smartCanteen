package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.order.OrderCountSummary;
import com.smart.canteen.dto.order.OrderSearch;
import com.smart.canteen.dto.recharge.RechargeLogSearch;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;
import com.smart.canteen.vo.OrderVo;
import com.smart.canteen.vo.RechargeLogVO;

import java.util.List;
import java.util.Map;

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
     * @param balance
     * @return
     */
    boolean addOrderForMachine(IcCard card, Double money, String machineNo, Double balance);

    /**
     * 添加消费订单
     *
     * @param card
     * @param money
     * @param account
     * @param balance
     * @return
     */
    boolean addOrderForDeduction(IcCard card, Double money, Account account, Double balance);

    /**
     * 消费记录
     *
     * @param search
     * @return
     */
    CommonList<OrderVo> listLogs(OrderSearch search);


    Map<String, Long> getSummaryDay();


}
