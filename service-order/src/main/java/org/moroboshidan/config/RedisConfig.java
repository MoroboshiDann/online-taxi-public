package org.moroboshidan.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private String port;
    private String protocol = "redis://";
    @Bean
    public RedissonClient redissonClient() {
        String address = protocol + host + ":" + port;
        Config config = new Config();
        config.useSingleServer().setAddress(address).setDatabase(0);
        return Redisson.create(config);
    }
}
