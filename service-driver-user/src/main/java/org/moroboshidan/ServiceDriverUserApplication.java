package org.moroboshidan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.moroboshidan.mapper")
@EnableDiscoveryClient
public class ServiceDriverUserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }
}
