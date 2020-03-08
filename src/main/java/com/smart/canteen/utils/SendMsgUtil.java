package com.smart.canteen.utils;

import com.smart.canteen.enums.CmdCodeEnum;
import com.smart.canteen.server.Packet;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * @author lc
 * @date 2020/3/8上午 2:15
 */
public class SendMsgUtil {

    public static DatagramPacket sendMsg(CmdCodeEnum code, String msg, InetAddress address, Packet receiveObj) throws UnsupportedEncodingException {
        byte[] gb2312s = new byte[64];
        msg = "欢迎使用IC消费机!" + msg;
        byte[] msgBytes = msg.getBytes("GB2312");
        System.arraycopy(msgBytes, 0, gb2312s, 0, Math.min(msgBytes.length, 64));
        int nSendFlag = 0;
        byte[] sendData = new byte[1024];
        sendData[nSendFlag] = receiveObj.getGuideCode();
        nSendFlag += 1;
        sendData[nSendFlag] = receiveObj.getSystemMachineNumber();
        nSendFlag += 1;
        System.arraycopy(receiveObj.getMachineAddrCode(), 0, sendData, 2, 2);
        nSendFlag += 2;
        int nLen = gb2312s.length;
        nLen += 1 + 3;
        byte[] blen = new byte[2];
        blen[0] = (byte) (nLen / 256);
        blen[1] = (byte) (nLen % 256);
        //packet len
        System.arraycopy(blen, 0, sendData, nSendFlag, 2);
        nSendFlag += 2;
        //cmd
        sendData[nSendFlag] = (byte) code.getCode();
        nSendFlag += 1;
        System.arraycopy(gb2312s, 0, sendData, nSendFlag, gb2312s.length);
        nSendFlag += gb2312s.length;
        byte[] voice = new byte[]{(byte) 0x01, (byte) 0x01, (byte) 0x31};
        System.arraycopy(voice, 0, sendData, nSendFlag, 3);
        nSendFlag += 3;
        byte[] crcCode = CrcUtil.calcCrc16(sendData, 2, nLen + 4);
        System.arraycopy(crcCode, 0, sendData, nSendFlag, 2);
        return new DatagramPacket(sendData, sendData.length, address, 3000);
    }

}
