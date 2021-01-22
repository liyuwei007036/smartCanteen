package com.smart.canteen.service;

import com.smart.canteen.vo.LoginLogVO;
import live.lumia.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.log.LoginSearch;
import com.smart.canteen.entity.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.canteen.enums.LoginEnum;

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
     * @param type
     */
    void addLog(Account account, String ip, LoginEnum type);

    /**
     * 登录查询
     *
     * @param search
     * @return
     */
    CommonList<LoginLogVO> listLogs(LoginSearch search);
}
