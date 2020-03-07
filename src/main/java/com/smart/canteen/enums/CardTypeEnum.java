package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 卡类别 暂时不知道用途
 *
 * @author lc
 * @date 2020/3/7下午 10:52
 */
@AllArgsConstructor
public enum CardTypeEnum implements IEnum<Integer> {
    /**
     *
     */
    ENABLE(0, "0"),

    DISABLE(1, "1");

    private Integer value;

    @JsonValue
    @Getter
    private String disPlay;


    @Override
    public Integer getValue() {
        return value;
    }
}
