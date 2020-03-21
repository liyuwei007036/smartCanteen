package com.smart.canteen.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lc
 * @date 2020/3/15下午 7:04
 */
@Component
public class MsgReceiver {

    @Autowired
    private WebSocket webSocket;

    public void readCard(String message) {
        webSocket.sendMsg(message);
    }

    public void updateMessage(String message) {
        webSocket.update();
    }
}
