package com.smart.canteen.server;

import com.smart.canteen.enums.CmdCodeEnum;
import com.smart.canteen.enums.ConEventEnum;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.utils.HexUtils;
import com.smart.canteen.utils.SendMsgUtil;
import com.smart.canteen.vo.ResponseMsg;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author lc
 * @date 2020/3/6下午 10:05
 */
@Slf4j
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
        while (true) {
            try {
                server.receive(packet);
                Packet recObj = new Packet(receiveData);
                InetAddress address = packet.getAddress();
                byte cmdCode = recObj.getCmdCode();
                ResponseMsg search;
                DatagramPacket responseData;
                if (cmdCode == (byte) CmdCodeEnum.CON.getCode()) {
                    String cardNo = String.valueOf(Math.abs(HexUtils.byteArrayToInt(recObj.getCardNumber())));
                    switch (ConEventEnum.getByCode(recObj.getEvent())) {
                        case QUERY_BALANCE:
                            search = iIcCardService.search(cardNo);
                            responseData = SendMsgUtil.sendMsg(search, address, recObj);
                            server.send(responseData);
                            break;
                        case NORMAL_REBATES:
                            int money = HexUtils.byteArrayToInt(recObj.getRealData());
                            search = iIcCardService.deductions(cardNo, money, String.valueOf(Math.abs(HexUtils.byteArrayToInt(recObj.getMachineAddrCode()))));
                            responseData = SendMsgUtil.sendMsg(search, address, recObj);
                            server.send(responseData);
                            break;
                        default:
                            break;
                    }
                } else {
                    search = new ResponseMsg(CmdCodeEnum.SEARCH);
                    responseData = SendMsgUtil.sendMsg(search, address, recObj);
                    server.send(responseData);
                }

            } catch (Exception e) {
                log.error("ooo", e);
            }
        }
    }
}
