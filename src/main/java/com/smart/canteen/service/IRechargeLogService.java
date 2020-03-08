package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.recharge.RechargeLogSearch;
import com.smart.canteen.entity.RechargeLog;
import com.smart.canteen.vo.RechargeLogVO;

import java.util.List;

/**
 * <p>
 * 充值记录 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-08
 */
public interface IRechargeLogService extends IService<RechargeLog> {

    /**
     * 添加充值记录
     *
     * @param logs
     */
    void addRechargeLogs(List<RechargeLog> logs);

    /**
     * 充值记录
     *
     * @param search
     * @return
     */
    CommonList<RechargeLogVO> listLogs(RechargeLogSearch search);

}
