package com.smart.canteen.server;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liyuwei
 */
@Slf4j
@Component
public class Subscribe {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private RedissonClient redissonClient;

    @PostConstruct
    public void subscribe() {
        RTopic rTopic = redissonClient.getTopic("readCard");
        // 接受订阅的消息
        rTopic.addListener(String.class, (charSequence, message) -> {
            log.info("接受到消息主题={}，内容={}", charSequence, message);
            webSocket.sendMsg(message);
        });

        RTopic updateTopic = redissonClient.getTopic("update");
        // 接受订阅的消息
        updateTopic.addListener(String.class, (charSequence, message) -> {
            log.info("接受到消息主题={}，内容={}", charSequence, message);
            webSocket.update();
        });
    }
}
