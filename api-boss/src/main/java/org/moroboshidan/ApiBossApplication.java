package org.moroboshidan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiBossApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ApiBossApplication.class, args);
    }
}
