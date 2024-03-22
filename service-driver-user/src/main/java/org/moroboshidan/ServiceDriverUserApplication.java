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
public class ServiceDriverUserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }
}
