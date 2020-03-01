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
    CREATE_MEMBER_FAIL(2001, "create  fail"),
    UPDATE_MEMBER_FAIL(2002, "update  fail");

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
