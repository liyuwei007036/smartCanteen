package com.smart.canteen.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lc
 * @date 2020/3/23下午 7:36
 */
@AllArgsConstructor
public enum LoginEnum implements IEnum<Integer> {
    /**
     *
     */
    LOGIN(0, "登录"),

    LOGIN_OUT(1, "登出");

    private final Integer value;

    @JsonValue
    @Getter
    private final String disPlay;


    @Override
    public Integer getValue() {
        return value;
    }
}
