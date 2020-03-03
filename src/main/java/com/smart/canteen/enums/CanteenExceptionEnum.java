package com.smart.canteen.enums;

import com.lc.core.error.IErrorInterface;
import lombok.AllArgsConstructor;

/**
 * @author lc
 * @date 2019-12-15
 */
@AllArgsConstructor
public enum CanteenExceptionEnum implements IErrorInterface {

    /**
     *
     */
    CREATE_FAIL(2001, "保存失败"),
    UPDATE_FAIL(2002, "更新失败"),
    USER_NOT_EXIST(2003, "用户不存在"),
    ACCOUNT_REPEAT(2004, "工号或身份证号或手机号重复"),
    USER_ACCOUNT_REPEAT(2005, "账号重复"),
    UPDATE_FA2IL(99999, "update  fail");

    private Integer code;

    private String msg;


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
