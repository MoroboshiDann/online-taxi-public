package org.moroboshidan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.moroboshidan.mapper")
public class ServiceOrderApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
