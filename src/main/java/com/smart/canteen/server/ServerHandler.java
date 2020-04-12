package com.smart.canteen.server;

import com.lc.core.utils.SpringUtil;
import com.smart.canteen.enums.CmdCodeEnum;
import com.smart.canteen.enums.ConEventEnum;
import com.smart.canteen.service.IIcCardService;
import com.smart.canteen.utils.ByteArrayUtils;
import com.smart.canteen.utils.SendMsgUtil;
import com.smart.canteen.vo.ResponseMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.io.IOException;

/**
 * @author lc
 * @date 2020/3/9上午 11:56
 */
public class ServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws IOException {
        ByteBuf buf = packet.copy().content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        Packet recObj = new Packet(req);
        byte cmdCode = recObj.getCmdCode();
        ResponseMsg msg;
        DatagramPacket responseData;
        if (CmdCodeEnum.CON == CmdCodeEnum.getByCode(cmdCode)) {
            IIcCardService iIcCardService = SpringUtil.getBean(IIcCardService.class);
            int cardNoi = ByteArrayUtils.byteArrayToInt(recObj.getCardNumber());
            String cardNo;
            if (cardNoi < 0) {
                cardNo = "0" + Math.abs(cardNoi);
            } else {
                cardNo = "1" + Math.abs(cardNoi);
            }
            byte[] machineAddrCode = recObj.getMachineAddrCode();
            switch (ConEventEnum.getByCode(recObj.getEvent())) {
                case QUERY_BALANCE:
                    msg = iIcCardService.search(cardNo, String.valueOf(ByteArrayUtils.byteArrayToShort(machineAddrCode)));
                    responseData = SendMsgUtil.sendMsg(msg, packet.sender(), recObj);
                    ctx.writeAndFlush(responseData);
                    break;
                case NORMAL_REBATES:
                    int money = ByteArrayUtils.byteArrayToInt(recObj.getRealData());
                    msg = iIcCardService.deductions(cardNo, money, String.valueOf(ByteArrayUtils.byteArrayToShort(machineAddrCode)));
                    responseData = SendMsgUtil.sendMsg(msg, packet.sender(), recObj);
                    ctx.writeAndFlush(responseData);
                    break;
                default:
                    break;
            }
        } else if (CmdCodeEnum.HART_BIT == CmdCodeEnum.getByCode(cmdCode)) {
            msg = ResponseMsg.normal();
            responseData = SendMsgUtil.sendMsg(msg, packet.sender(), recObj);
            ctx.writeAndFlush(responseData);

            try {
                Thread.sleep(5000);
                msg = ResponseMsg.setTitle();
                responseData = SendMsgUtil.sendMsg(msg, packet.sender(), recObj);
                ctx.writeAndFlush(responseData);
            } catch (InterruptedException ignored) {

            }
        } else {
            msg = new ResponseMsg(CmdCodeEnum.SEARCH);
            responseData = SendMsgUtil.sendMsg(msg, packet.sender(), recObj);
            ctx.writeAndFlush(responseData);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.flush();
    }

}
