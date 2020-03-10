package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lc
 * @date 2020/3/10下午 9:23
 */
@AllArgsConstructor
public enum EmployeeStatusEnum implements IEnum<Integer> {
    /**
     *
     */
    ENABLE(1, "在职"),

    DISABLE(2, "离职");

    private Integer value;

    @JsonValue
    @Getter
    private String disPlay;


    @Override
    public Integer getValue() {
        return value;
    }
}
