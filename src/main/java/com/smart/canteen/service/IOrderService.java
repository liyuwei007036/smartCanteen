package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import live.lumia.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.OrderSummaryDTO;
import com.smart.canteen.dto.SummaryDTO;
import com.smart.canteen.dto.SummarySearchDTO;
import com.smart.canteen.dto.order.OrderSearch;
import com.smart.canteen.entity.IcCard;
import com.smart.canteen.entity.Order;
import com.smart.canteen.vo.OrderVo;
import com.smart.canteen.vo.SummaryVO;

import java.util.Date;

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
     * 添加消费订单(补扣)
     *
     * @param card
     * @param money
     * @param account
     * @param balance
     * @param desc
     * @return
     */
    boolean addOrderForDeduction(IcCard card, Double money, Account account, Double balance, String desc);

    /**
     * 消费记录
     *
     * @param search
     * @return
     */
    CommonList<OrderVo> listLogs(OrderSearch search);


    /**
     * 柱状图年
     *
     * @return
     */
    SummaryDTO getYearSaleData();

    /**
     * 柱状图月
     *
     * @return
     */
    SummaryDTO getMonthSaleData();


    /**
     * 柱状图天
     *
     * @return
     */
    SummaryDTO getDaySaleData();

    /**
     * 统计数据
     *
     * @return
     */
    SummaryVO getUpdateData();

    /**
     * 统计2个时间段内的柱状图数据
     *
     * @param params
     * @return
     */
    SummaryDTO getOtherSaleData(SummarySearchDTO params);

    /**
     * 获取莫个时间段内的消费记录
     *
     * @param start
     * @param end
     * @return
     */
    OrderSummaryDTO getOrderTotal(Date start, Date end);
}
