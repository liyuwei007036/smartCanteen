package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lc
 * @date 2020/3/8下午 5:53
 */
@AllArgsConstructor
public enum RechargeTypeEnum implements IEnum<Integer> {
    /**
     * 充值类型
     */
    NORMAL(1, "正常"),
    REFUND(2, "退费");

    private Integer value;

    @JsonValue
    @Getter
    private String disPlay;

    @Override
    public Integer getValue() {
        return value;
    }
}
