package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lc
 * @date 2020/3/8下午 8:18
 */
@AllArgsConstructor
public enum OrderTypeEnum implements IEnum<Integer> {
    /**
     * 消费类型
     */
    NORMAL(1, "正常"),
    FILL_BUCKLE(2, "补扣");

    private final Integer value;

    @JsonValue
    @Getter
    private final String disPlay;

    @Override
    public Integer getValue() {
        return value;
    }
}