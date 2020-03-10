package com.smart.canteen.utils;

import com.lc.core.dto.Account;
import com.lc.core.enums.BaseErrorEnums;
import com.lc.core.error.BaseException;
import com.smart.canteen.entity.BaseEntity;

import java.util.Date;

/**
 * @author lc
 * @date 2020/3/3下午 10:24
 */
public class EntityLogUtil {

    public static void addNormalUser(BaseEntity entity, Account operation) {
        if (entity == null || operation == null) {
            throw new BaseException(BaseErrorEnums.SYSTEM_ERROR);
        }
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setCreatorId(operation.getId());
        entity.setCreatorAccount(operation.getAccount());
        entity.setCreatorName(operation.getName());
        entity.setLastUpdateId(operation.getId());
        entity.setLastUpdateName(operation.getName());
        entity.setLastUpdateAccount(operation.getAccount());
        entity.setLastUpdateTime(now);
    }
}
