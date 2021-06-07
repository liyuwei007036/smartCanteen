package com.smart.canteen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 消费指令事件
 *
 * @author lc
 * @date 2020/3/7下午 11:05
 */
@AllArgsConstructor
public enum ConEventEnum {
    /**
     * 正常减款
     */
    NORMAL_REBATES(0x00),

    /**
     * 菜单模式减款
     */
    MENU_MODE(0x01),

    /**
     * 正常退款
     */
    NORMAL_REFUND(0x02),

    /**
     * 加款模式下退款
     */
    REFUND_UNDER_CHARGE_MODE(0x03),

    /**
     * 退总金额
     */
    REFUND_THE_TOTAL_AMOUNT(0x04),

    /**
     * 正常加款
     */
    NORMAL_AND_MODEL(0x05),

    /**
     * 查询余额
     */
    QUERY_BALANCE(0x06),

    /**
     * 下传消费
     */
    UNDER_THE_CONSUMPTION(0x07);

    private final int code;

    public static ConEventEnum getByCode(byte code) {
        return Arrays.stream(ConEventEnum.values()).filter(x -> x.code == code).findFirst().orElse(null);
    }
}
