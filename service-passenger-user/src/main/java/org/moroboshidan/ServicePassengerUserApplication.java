package org.moroboshidan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("org.moroboshidan.mapper")
public class ServicePassengerUserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServicePassengerUserApplication.class, args);
    }
}
