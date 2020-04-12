package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 卡状态
 *
 * @author lc
 * @date 2020/3/3下午 9:25
 */
@AllArgsConstructor
public enum CardStatusEnum implements IEnum<Integer> {
    /**
     * 激活
     */
    ENABLE(1, "激活"),

    /**
     * 禁止
     */
    DISABLE(2, "禁止");

    private final Integer value;

    @JsonValue
    @Getter
    private final String disPlay;


    @Override
    public Integer getValue() {
        return value;
    }
}
