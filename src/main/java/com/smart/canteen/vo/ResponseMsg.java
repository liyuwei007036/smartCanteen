package com.smart.canteen.vo;

import com.smart.canteen.enums.CmdCodeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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


    public ResponseMsg(CmdCodeEnum code, byte[] voice, String cardNo, Double balance, String name) {
        this.code = code.getCode();
        this.voice = voice;
        byte[] data = new byte[64];
        try {
            List<String> msg = new ArrayList<>();
            msg.add(0, "欢迎使用IC卡消费机");
            msg.add(1, "卡号：" + cardNo);
            if (balance != null) {
                msg.add(2, "姓名：" + name);
            }
            if (balance != null) {
                msg.add(3, "余额：" + balance);
            }
            for (int i = 0; i < msg.size(); i++) {
                byte[] m = msg.get(i).getBytes("GB2312");
                int index = 16 * i;
                if (m.length < 16) {
                    int left = (16 - m.length) / 2;
                    index = index + left;
                }
                System.arraycopy(m, index, data, 16 * i, 16);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("转换失败", e);
        }
        this.msg = data;
    }
}
