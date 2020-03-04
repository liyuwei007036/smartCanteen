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
public class CardStatusEnum implements IEnum<Integer> {

    private Integer value;

    @JsonValue
    @Getter
    private String disPlay;


    @Override
    public Integer getValue() {
        return value;
    }
}
