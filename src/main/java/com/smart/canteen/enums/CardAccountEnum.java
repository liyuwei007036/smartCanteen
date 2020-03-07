package com.smart.canteen.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

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
    LOSS(1, "挂失"),
    UN_LOSS(1, "解卦"),
    QUIT(5, "已退卡"),
    REISSUE(1, "解卦");

    private Integer value;

    @JsonValue
    private String disPlay;

    @Override
    public Integer getValue() {
        return value;
    }

    // 正常， 挂失 解卦 已退卡 已补卡


}
