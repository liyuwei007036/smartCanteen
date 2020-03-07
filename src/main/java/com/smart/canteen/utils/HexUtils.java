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
        return String.format("%x", aByte);
    }

    public static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuffer.append(String.format("%x", aByte));
        }
        return stringBuffer.toString();
    }

    public static long byteArrayToLong(byte[] data) throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bai);
        return dis.readLong();
    }

    public static int byteArrayToInt(byte[] data) throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bai);
        return dis.readInt();
    }

}
