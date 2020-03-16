package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.core.dto.Account;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.log.LoginSearch;
import com.smart.canteen.entity.LoginLog;
import com.smart.canteen.mapper.LoginLogMapper;
import com.smart.canteen.service.ILoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Async
    @Override
    public void addLog(Account account, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setEmpName(account.getName());
        loginLog.setEmpNo(account.getAccount());
        loginLog.setLoginTime(new Date());
        loginLog.setIp(ip);
        save(loginLog);
    }

    @Override
    public CommonList<LoginLog> listLogs(LoginSearch search) {
        Page<LoginLog> page = new Page<>(search.getPage(), search.getSize());
        page(page, Wrappers.<LoginLog>lambdaQuery()
                .eq(StringUtils.isNotEmpty(search.getEmpName()), LoginLog::getEmpName, search.getEmpName())
                .ge(Objects.nonNull(search.getStart()), LoginLog::getLoginTime, search.getStart())
                .lt(Objects.nonNull(search.getEnd()), LoginLog::getLoginTime, search.getEnd()).orderByDesc(LoginLog::getLoginTime)
        );
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());

    }
}
