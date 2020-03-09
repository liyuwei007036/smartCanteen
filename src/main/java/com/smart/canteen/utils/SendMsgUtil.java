package com.smart.canteen.utils;

import com.smart.canteen.server.Packet;
import com.smart.canteen.vo.ResponseMsg;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;


/**
 * @author lc
 * @date 2020/3/8上午 2:15
 */
public class SendMsgUtil {

    public static DatagramPacket sendMsg(ResponseMsg msg, InetSocketAddress address, Packet receiveObj) {
        byte[] msgBytes = msg.getMsg();
        int nSendFlag = 0;
        byte[] sendData = new byte[1024];
        sendData[nSendFlag] = receiveObj.getGuideCode();
        nSendFlag += 1;
        sendData[nSendFlag] = receiveObj.getSystemMachineNumber();
        nSendFlag += 1;
        System.arraycopy(receiveObj.getMachineAddrCode(), 0, sendData, 2, 2);
        nSendFlag += 2;
        int nLen = msgBytes.length;
        nLen += 1 + 3;
        byte[] blen = new byte[2];
        blen[0] = (byte) (nLen / 256);
        blen[1] = (byte) (nLen % 256);
        //packet len
        System.arraycopy(blen, 0, sendData, nSendFlag, 2);
        nSendFlag += 2;
        //cmd
        sendData[nSendFlag] = (byte) msg.getCode();
        nSendFlag += 1;
        System.arraycopy(msgBytes, 0, sendData, nSendFlag, msgBytes.length);
        nSendFlag += msgBytes.length;
        byte[] voice = msg.getVoice();
        System.arraycopy(voice, 0, sendData, nSendFlag, 3);
        nSendFlag += 3;
        byte[] crcCode = CrcUtil.calcCrc16(sendData, 2, nLen + 4);
        System.arraycopy(crcCode, 0, sendData, nSendFlag, 2);
        return new DatagramPacket(Unpooled.copiedBuffer(sendData), address);
    }

}
