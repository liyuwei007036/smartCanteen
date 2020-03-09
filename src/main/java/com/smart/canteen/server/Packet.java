package com.smart.canteen.server;

import com.lc.core.error.BaseException;
import com.smart.canteen.enums.CanteenExceptionEnum;
import com.smart.canteen.enums.CmdCodeEnum;
import com.smart.canteen.utils.CrcUtil;
import com.smart.canteen.utils.ByteArrayUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

/**
 * 数据包
 * 通信包格式为： 引导码（1）	系统设备号（1）	机器地址码（2）	数据包长度（2）	数据包	校验码（2）
 *
 * @author lc
 * @date 2020/3/6下午 8:50
 */
@Slf4j
@Getter
public class Packet {

    /**
     * 	引导码：1 byte = 0xFE data[0]
     */
    private byte guideCode = 0;

    /**
     * 	系统设备号 1 byte= 0x01  data[1]
     */
    private byte systemMachineNumber = 0;

    /**
     * 	机器地址码：	2 byte= 1 – 999（0xFFFF为广播地址，0x00为系统保留） data[2] data[3]
     */
    private byte[] machineAddrCode = new byte[2];

    /**
     * 	数据包长度：	2 byte= 0 – 0x07FF，表示紧跟数据包长度；data[4], data[5]
     */
    private byte[] dataLen = new byte[2];

    /**
     * 	数据包：	(变长)长度可变，由数据包长度指定；
     * 数据包的格式为： 命令码	数据码1	数据码2	…	数据码n
     * 	命令码：1 byte
     * 	数据码：数据内容。
     * 从第7位开始(下标为6)为数据包
     */
    private byte[] data = new byte[1024];

    /**
     * 	校验码：	2 byte= 计算从地址码开始到数据包最后一个字节的CRC16码。
     */
    private byte[] crcCode = new byte[2];

    /**
     * 指令码  data[6]
     */
    private byte cmdCode = 0;

    private int nDataLen = 0;

    /**
     * 卡号
     */
    private byte[] cardNumber = new byte[4];

    /**
     * 事件码
     */
    private byte event = 0;

    /**
     * 真正的数据包 data[7]
     */
    private byte[] realData;

    private Packet() {

    }

    public Packet(byte[] dataBytes) throws IOException {
        // 引导码 0
        this.guideCode = dataBytes[0];
        // 系统设备号 1
        this.systemMachineNumber = dataBytes[1];
        // 机器地址码 2 3
        System.arraycopy(dataBytes, 2, this.machineAddrCode, 0, 2);
        // 数据长度 4 5
        System.arraycopy(dataBytes, 4, this.dataLen, 0, 2);
        // 指令码 6
        this.cmdCode = dataBytes[6];
        // 不包含指令码数据包 = 数据长度 -1
        int dataLength = ByteArrayUtils.byteArrayToShort(this.dataLen) - 1;
        this.data = new byte[dataLength];
        // 数据包从第7为开始
        System.arraycopy(dataBytes, 7, this.data, 0, dataLength);
        // crc
        System.arraycopy(dataBytes, dataLength + 7, this.crcCode, 0, 2);
        // crc 计算方式 从第2位开始 dataLength + 7 - 2结束
        byte[] crcResult = CrcUtil.calcCrc16(dataBytes, 2, dataLength + 5);
        if (!Arrays.equals(crcResult, crcCode)) {
            throw new BaseException(CanteenExceptionEnum.SIGN_ERROR);
        }
        CmdCodeEnum cmd = CmdCodeEnum.getByCode(this.cmdCode);
        if (CmdCodeEnum.CON == cmd) {
            // 卡号
            System.arraycopy(this.data, 0, this.cardNumber, 0, 4);
            // 事件码
            event = this.data[4];
            // 真实数据包
            this.realData = new byte[data.length - 5];
            System.arraycopy(this.data, 5, this.realData, 0, data.length - 5);
        } else {
            log.warn("数据包:" + Arrays.toString(this.data));
        }
    }


}
