package com.smart.canteen.service;

import com.lc.core.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.log.LoginSearch;
import com.smart.canteen.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
public interface ILoginLogService extends IService<LoginLog> {

    /**
     * 添加登陆记录
     *
     * @param account
     * @param ip
     */
    void addLog(Account account, String ip);

    /**
     * 登录查询
     *
     * @param search
     * @return
     */
    CommonList<LoginLog> listLogs(LoginSearch search);
}
