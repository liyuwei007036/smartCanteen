package com.smart.canteen.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author lc
 * @date 2020/3/6下午 10:13
 */
public class HexUtils {

    public static String byte2Hex(byte aByte) {
        String temp = Integer.toHexString(aByte & 0xFF);
        if (temp.length() == 1) {
            //得到一位的进行补0操作
            return 0 + temp;
        } else {
            return temp;
        }
    }

    public static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                //得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static long byteArrayToLong(byte[] data) throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(data);
        DataInputStream dis =new DataInputStream(bai);
        return dis.readLong();
    }
    public static int byteArrayToInt(byte[] data) throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(data);
        DataInputStream dis =new DataInputStream(bai);
        return dis.readInt();
    }


    public static void main(String[] args) throws IOException {
        byte[] m = new byte[]{0x00, 0x00, 0x00, 0x64};
        String s = byte2Hex(m);
        System.out.println(byteArrayToInt(m));
    }
}
