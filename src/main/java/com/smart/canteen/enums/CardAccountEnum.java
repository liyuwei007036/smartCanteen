package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lc
 * @date 2020/3/7下午 10:46
 */
@AllArgsConstructor
public enum CardAccountEnum implements IEnum<Integer> {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 挂失
     */
    LOSS(2, "挂失"),
    /**
     * 解挂
     */
    UN_LOSS(3, "解挂"),
    /**
     * 已退卡
     */
    QUIT(4, "已退卡"),
    /**
     * 已补卡
     */
    REISSUE(5, "已补卡");

    private final Integer value;

    @Getter
    @JsonValue
    private final String disPlay;

    @Override
    public Integer getValue() {
        return value;
    }

}
