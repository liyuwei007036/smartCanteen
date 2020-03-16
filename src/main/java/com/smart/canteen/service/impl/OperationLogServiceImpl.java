package com.smart.canteen.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lc.core.dto.Account;
import com.smart.canteen.annotations.Log;
import com.smart.canteen.entity.OperationLog;
import com.smart.canteen.mapper.OperationLogMapper;
import com.smart.canteen.service.IOperationLogService;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

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
            JSONObject res = new JSONObject();
            String action = slog.action();
            String module = slog.module();
            Class<?> clazz1 = slog.clazz();
            if (clazz1 != void.class) {
                JSONObject da = JSON.parseObject(objectMapper.writeValueAsString(args[0]));
                da.forEach((k, v) -> {
                    try {
                        if (!StringUtils.isEmpty(v)) {
                            ApiModelProperty desc = clazz1.getDeclaredField(k).getAnnotation(ApiModelProperty.class);
                            if (desc != null) {
                                res.put(desc.value(), v);
                            }
                        }
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                res.put(slog.dataDesc(), args[0].toString());
            }
            OperationLog operationLog = new OperationLog();
            operationLog.setAction(action);
            operationLog.setEmpName(account.getName());
            operationLog.setModule(module);
            operationLog.setDetail(res.toString());
            operationLog.setOperationTime(new Date());
            operationLog.setEmpNo(account.getAccount());
            save(operationLog);
        } catch (Exception e) {
            log.error("添加操作记录失败", e);
        }
    }
}
