package com.smart.canteen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.core.dto.Account;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.log.OperationSearch;
import com.smart.canteen.entity.OperationLog;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
public interface IOperationLogService extends IService<OperationLog> {

    /**
     * 添加日志
     *
     * @param args
     * @param log
     * @param account
     */
    void addLog(Object[] args, Log log, Account account);

    /**
     * 查询
     *
     * @param search
     * @return
     */
    CommonList<OperationLog> listLogs(OperationSearch search);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    OperationLog getById(Long id);
}
