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
     * 卡账户状态
     */
    NORMAL(1, "正常"),
    LOSS(2, "挂失"),
    UN_LOSS(3, "解挂"),
    QUIT(4, "已退卡"),
    REISSUE(5, "已补卡");

    private Integer value;

    @Getter
    @JsonValue
    private String disPlay;

    @Override
    public Integer getValue() {
        return value;
    }

    // 正常， 挂失 解卦 已退卡 已补卡


}
