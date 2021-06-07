package com.smart.canteen.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.dto.CommonList;
import com.smart.canteen.dto.log.OperationSearch;
import com.smart.canteen.entity.OperationLog;
import com.smart.canteen.mapper.OperationLogMapper;
import com.smart.canteen.service.IOperationLogService;
import live.lumia.dto.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author lc
 * @since 2020-03-16
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    @Override
    public void addLog(Object[] args, Log slog, Account account) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String action = slog.action();
            String module = slog.module();
            String res = objectMapper.writeValueAsString(args);
            OperationLog operationLog = new OperationLog();
            operationLog.setAction(action);
            operationLog.setEmpName(account.getName());
            operationLog.setModule(module);
            operationLog.setDetail(res);
            operationLog.setOperationTime(new Date());
            operationLog.setEmpNo(account.getAccount());
            save(operationLog);
        } catch (Exception e) {
            log.error("添加操作记录失败", e);
        }
    }


    @Override
    public CommonList<OperationLog> listLogs(OperationSearch search) {
        Page<OperationLog> page = new Page<>(search.getPage(), search.getSize());
        page(page, Wrappers.<OperationLog>lambdaQuery()
                .eq(StringUtils.isNotBlank(search.getEmpName()), OperationLog::getEmpName, search.getEmpName())
                .eq(StringUtils.isNotBlank(search.getModule()), OperationLog::getModule, search.getModule())
                .eq(StringUtils.isNotBlank(search.getAction()), OperationLog::getAction, search.getAction())
                .ge(Objects.nonNull(search.getStart()), OperationLog::getOperationTime, search.getStart())
                .lt(Objects.nonNull(search.getEnd()), OperationLog::getOperationTime, search.getEnd()).orderByDesc(OperationLog::getOperationTime)
        );
        return new CommonList<>(page.hasNext(), page.getTotal(), page.getCurrent(), page.getRecords());
    }

    @Override
    public OperationLog getById(Long id) {
        return super.getById(id);
    }
}
