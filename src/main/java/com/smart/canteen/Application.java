package com.smart.canteen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author l5990
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.lc.core", "com.smart.canteen"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
