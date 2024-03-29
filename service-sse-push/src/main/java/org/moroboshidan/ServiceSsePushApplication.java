package org.moroboshidan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 司机哭护短模拟启动类
 * @author: MoroboshiDan
 * @time: 2024/3/29 17:53
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceSsePushApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSsePushApplication.class, args);
    }
}
