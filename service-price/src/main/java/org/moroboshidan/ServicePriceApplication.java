package org.moroboshidan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("org.moroboshidan.mapper")
public class ServicePriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePriceApplication.class, args);
    }

}
