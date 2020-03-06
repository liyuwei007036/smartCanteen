package com.smart.canteen.server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author lc
 * @date 2020/3/6下午 10:05
 */
@Slf4j
public class SocketServer implements Runnable {
    private final static int UDP_PORT = 3000;

    @SneakyThrows
    @Override
    public void run() {
        byte[] receiveData = new byte[1024];
        DatagramSocket server = new DatagramSocket(UDP_PORT);
        DatagramPacket packet = new DatagramPacket(receiveData, receiveData.length);
        do {
            try {
                server.receive(packet);
                new Packet(receiveData);
            } catch (Exception e) {
                log.error("ooo", e);
            }
        } while (true);
    }


    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.run();
    }
}
