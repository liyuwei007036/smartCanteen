package com.smart.canteen.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lc.core.service.RedisService;
import com.lc.core.utils.ObjectUtil;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
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

    private static Map<String, Map<String, Session>> map = new HashMap<>();
    private static final String READ_CARD = "readCard";
    private static final String SUMMARY = "summary";

    @Autowired
    private RedisService redisService;

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        session.setSubprotocols("stomp");
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap) {
        System.out.println("one connection onOpen");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
        String token = ObjectUtil.getString(session.getAttribute("token"));
        String type = ObjectUtil.getString(session.getAttribute("type"));
        if (!StringUtils.isEmpty(type)) {
            Map<String, Session> stringSessionMap = map.get(type);
            if (stringSessionMap != null) {
                stringSessionMap.remove(token);
            }
        }
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
        String type = msg.getString("type");
        String token = msg.getString("token");
        if (!StringUtils.isEmpty(type) && !StringUtils.isEmpty(token) && start) {
            Map<String, Session> stringSessionMap = map.computeIfAbsent(type, k -> new HashMap<>(16));
            session.setAttribute("type", type);
            session.setAttribute("token", token);
            stringSessionMap.put(token, session);
        }
        if (READ_CARD.equals(type)) {
            if (start) {
                redisService.put("GET_CARD_NO", true, 9, 30);
            } else {
                Map<String, Session> map2 = map.get(type);
                if (map2 != null) {
                    map2.remove(token);
                }
                redisService.remove("GET_CARD_NO", 9);
            }
        } else if (SUMMARY.equals(type)) {
            if (!start) {
                Map<String, Session> map2 = map.get(type);
                if (map2 != null) {
                    map2.remove(token);
                }
            }
        }
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
        Map<String, Session> readCard = map.get(READ_CARD);
        if (readCard != null) {
            readCard.forEach((key, value) -> value.sendText(msg));
        }
    }

    public void update() {
        Map<String, Session> summaryMap = map.get(SUMMARY);
        if (summaryMap != null) {
            summaryMap.forEach((key, value) -> value.sendText("update"));
        }

    }
}
