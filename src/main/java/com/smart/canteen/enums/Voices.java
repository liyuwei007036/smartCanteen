package com.smart.canteen.enums;

/**
 * @author lc
 * @date 2020/3/8下午 12:03
 */
public class Voices {

    /**
     * 消费成功
     */
    public static final byte[] SUCCESS = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x31};

    /**
     * 无效卡
     */
    public static final byte[] INVALID = new byte[]{(byte) 0x03, (byte) 0x03, (byte) 0x25};

    /**
     * 此卡已注销
     */
    public static final byte[] CANCELLATION = new byte[]{(byte) 0x03, (byte) 0x03, (byte) 0x24};

    /**
     * 挂失卡
     */
    public static final byte[] LOSS = new byte[]{(byte) 0x03, (byte) 0x03, (byte) 0x26};

    /**
     * 本次消费
     */
    public static final byte[] COST = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x19};


    /**
     * 余额
     */
    public static final byte[] BALANCE = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x32};


    /**
     * 谢谢
     */
    public static final byte[] THANKS = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x27};


    /**
     * 欢迎使用消费机
     */
    public static final byte[] WELCOME = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x30};


    /**
     * 余额不足
     */
    public static final byte[] NOT = new byte[]{(byte) 0x03, (byte) 0x03, (byte) 0x21};

    /**
     * 警告音
     */
    public static final byte[] WARN = new byte[]{(byte) 0x03, (byte) 0x03, (byte) 0x03};
}
