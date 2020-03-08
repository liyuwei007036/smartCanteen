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
     * 充值类型
     */
    MACHINE(1, "刷卡机"),
    WANGYI(2, "网易严选");

    private Integer value;

    @JsonValue
    @Getter
    private String disPlay;

    @Override
    public Integer getValue() {
        return value;
    }
}