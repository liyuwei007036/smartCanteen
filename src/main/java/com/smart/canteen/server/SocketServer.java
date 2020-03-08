package com.smart.canteen.server;

import com.smart.canteen.enums.CmdCodeEnum;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.utils.HexUtils;
import com.smart.canteen.utils.SendMsgUtil;
import com.smart.canteen.vo.CardVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author lc
 * @date 2020/3/6下午 10:05
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Component
public class SocketServer implements Runnable {
    private final static int UDP_PORT = 3000;

    @Autowired
    private IIcCardService iIcCardService;

    @SneakyThrows
    @Override
    public void run() {
        byte[] receiveData = new byte[1024];
        DatagramSocket server = new DatagramSocket(UDP_PORT);
        DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
        log.info("------------------------------------启动监听程序---------------------------");
        do {
            try {
                server.receive(packet);
                Packet recObj = new Packet(receiveData);
                InetAddress address = packet.getAddress();
                byte cmdCode = recObj.getCmdCode();
                if (cmdCode == 0x63 && recObj.getEvent() == 0x06) {
                    CardVo byNo = iIcCardService.getByNo(HexUtils.byte2Hex(recObj.getCardNumber()));
                    String msg = String.valueOf(HexUtils.byteArrayToInt(recObj.getCardNumber()));
                    if (byNo != null) {
                        msg += byNo.getEmpName();
                    }
                    DatagramPacket packet1 = SendMsgUtil.sendMsg(CmdCodeEnum.CON, msg, address, recObj);
                    server.send(packet1);
                } else if (cmdCode == 0x63 && recObj.getEvent() == 0x00) {
                    int i = HexUtils.byteArrayToInt(recObj.getRealData());
                    CardVo byNo = iIcCardService.getByNo(HexUtils.byte2Hex(recObj.getCardNumber()));
                    DatagramPacket packet1 = SendMsgUtil.sendMsg(CmdCodeEnum.CON, byNo.getEmpName(), address, recObj);
                    log.info("消费金额 {}", i);
                    server.send(packet1);
                }
            } catch (Exception e) {
                log.error("ooo", e);
            }
        } while (true);
    }

}
