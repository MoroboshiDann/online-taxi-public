package org.moroboshidan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.moroboshidan.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class    ServiceDriverUserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }
}
