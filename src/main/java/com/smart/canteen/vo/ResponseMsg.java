package com.smart.canteen.vo;

import com.lc.core.utils.MathUtil;
import com.smart.canteen.enums.CmdCodeEnum;
import com.smart.canteen.enums.Voices;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lc
 * @date 2020/3/8上午 11:41
 */
@Slf4j
@Data
public class ResponseMsg {

    /**
     * 消息
     */
    private byte[] msg;

    /**
     * 指令码
     */
    private int code;

    /**
     * 声音
     */
    private byte[] voice;

    /**
     * 消费成功
     *
     * @param code
     * @param voice
     * @param cardNo
     * @param balance
     * @param cost
     */
    public ResponseMsg(CmdCodeEnum code, byte[] voice, String cardNo, Double balance, Integer cost) {
        this.code = code.getCode();
        this.voice = voice;
        byte[] data = new byte[64];
        try {
            List<String> msg = new ArrayList<>();
            msg.add(0, "欢迎使用IC卡消费机");
            msg.add(1, "卡号:" + cardNo);
            msg.add(2, "当前消费:" + MathUtil.div(cost, 100, 2));
            msg.add(3, "余额:" + balance);
            for (int i = 0; i < msg.size(); i++) {
                byte[] m = msg.get(i).getBytes("GB2312");
                int index = 16 * i;
                System.arraycopy(m, 0, data, index, Math.min(m.length, 16));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("转换失败", e);
        }
        this.msg = data;
    }

    /**
     * 余额
     *
     * @param code
     * @param voice
     * @param cardNo
     * @param balance
     */
    public ResponseMsg(CmdCodeEnum code, byte[] voice, String cardNo, Double balance) {
        this.code = code.getCode();
        this.voice = voice;
        byte[] data = new byte[64];
        try {
            List<String> msg = new ArrayList<>();
            msg.add(0, "欢迎使用IC消费机");
            msg.add(1, "卡号:" + cardNo);
            msg.add(2, "余额:" + MathUtil.div(balance, 1, 2));
            for (int i = 0; i < msg.size(); i++) {
                byte[] m = msg.get(i).getBytes("GB2312");
                int index = 16 * i;
                System.arraycopy(m, 0, data, index, Math.min(m.length, 16));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("转换失败", e);
        }
        this.msg = data;
    }

    public ResponseMsg(CmdCodeEnum code, byte[] voice, String cardNo, String other) {
        this.code = code.getCode();
        this.voice = voice;
        byte[] data = new byte[64];
        try {
            List<String> msg = new ArrayList<>();
            msg.add(0, "欢迎使用IC消费机");
            msg.add(1, "卡号:" + cardNo);
            if (!StringUtils.isEmpty(other)) {
                msg.add(2, other);
            }
            for (int i = 0; i < msg.size(); i++) {
                byte[] m = msg.get(i).getBytes("GB2312");
                int index = 16 * i;
                System.arraycopy(m, 0, data, index, Math.min(m.length, 16));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("转换失败", e);
        }
        this.msg = data;
    }


    public ResponseMsg(CmdCodeEnum code) {
        this.code = code.getCode();
        this.voice = Voices.THANKS;
        byte[] data = new byte[64];
        try {
            List<String> msg = new ArrayList<>();
            msg.add(0, "欢迎使用IC消费机");
            msg.add(1, "网络连接正常!");
            for (int i = 0; i < msg.size(); i++) {
                byte[] m = msg.get(i).getBytes("GB2312");
                int index = 16 * i;
                System.arraycopy(m, 0, data, index, Math.min(m.length, 16));
            }
        } catch (UnsupportedEncodingException e) {
            log.error("转换失败", e);
        }
        this.msg = data;
    }

}
