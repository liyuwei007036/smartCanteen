package com.smart.canteen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author lc
 * @date 2020/3/7下午 8:46
 */
@AllArgsConstructor
public enum CmdCodeEnum {
    /**
     * 消费指令0x63
     */
    CON(0x63),

    /**
     * 查询按键 （0X00:消费的笔数和金额 0X01:加款的笔数和金额，0X02：退全部余额的笔数和金额)
     */
    SEARCH(0x70),
    /**
     * 心跳包
     */
    HART_BIT(0x74),

    /**
     * 设置机器时间
     */
    TIME(0x30),

    /**
     * 设置TITLE
     */
    TITLE(0x48),
    NORMAL(0x81),


    ;

    @Getter
    private final int code;

    public static CmdCodeEnum getByCode(byte code) {
        return Arrays.stream(CmdCodeEnum.values()).filter(x -> x.code == code).findFirst().orElse(null);
    }


}
