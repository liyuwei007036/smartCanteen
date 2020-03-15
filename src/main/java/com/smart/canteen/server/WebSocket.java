package com.smart.canteen.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lc.core.service.RedisService;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lc
 * @date 2020/3/15下午 3:34
 */
@Slf4j
@ServerEndpoint(path = "/ws/{arg}", port = "${websocket.port}")
public class WebSocket {

    private static Map<String, Session> map = new HashMap<>();

    @Autowired
    private RedisService redisService;

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        session.setSubprotocols("stomp");
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
        Object arg = session.getAttribute("session");
        map.remove(arg.toString(), session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("webSocket 接收到消息{}", message);
        JSONObject msg = JSON.parseObject(message);
        Boolean start = msg.getBoolean("start");
        String token = msg.getString("token");
        if (start) {
            redisService.put("GET_CARD_NO", token, 9, 30);
        } else {
            redisService.remove("GET_CARD_NO", 9);
        }
        map.put(token, session);
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

    public void sendMsg(String msg) {
        map.forEach((key, value) -> value.sendText(msg));
    }
}
