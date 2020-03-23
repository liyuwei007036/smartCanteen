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

    @Scheduled(cron = "0 0,30 * * * ? ")
    public void update() {
        try {
            webSocket.update();
        } catch (Exception ignored) {
        }
    }
}
