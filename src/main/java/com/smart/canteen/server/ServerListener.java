package com.smart.canteen.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author lc
 * @date 2020/3/8上午 1:43
 */
@Component
public class ServerListener implements CommandLineRunner {

    @Autowired
    private NettyServer server;


    @Value("${netty.port}")
    private int port;

    @Override
    public void run(String... args) {
        server.run(port);
    }
}
