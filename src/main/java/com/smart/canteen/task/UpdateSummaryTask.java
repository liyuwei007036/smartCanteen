package com.smart.canteen.task;

import com.lc.core.service.RedisService;
import com.smart.canteen.server.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lc
 * @date 2020/3/21下午 9:10
 */
@Component
@EnableScheduling
public class UpdateSummaryTask {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private RedisService redisService;

    @Scheduled(cron = "0 0,30 * * * ? ")
    public void update() {
        try {
            boolean f = redisService.putIfAbsent("UPDATE_SUMMARY", 1, 9, 60);
            if (f) {
                webSocket.update();
                redisService.remove("UPDATE_SUMMARY", 9);
            }
        } catch (Exception e) {
            redisService.remove("UPDATE_SUMMARY", 9);
        }
    }
}
