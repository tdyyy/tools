package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: tangdy
 * @Date: 2020/12/14 15:27
 * @Vision: 1.0
 */
@Configuration
public class RedisConfig {
//    @Value("${spring.redis.url}")
//    private String url;
//    @Value("${spring.redis.port}")
//    private int port;
    @Value("${spring.redis.sentinel.nodes}")
    private List<String> nodes;
    @Value("${spring.redis.sentinel.password}")
    private String password;
    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Bean
    public JedisSentinelPool JedisPoolFactory(){
        Set<String> set = new HashSet<>();
        nodes.forEach(set::add);
        JedisPoolConfig config = new JedisPoolConfig();
        JedisSentinelPool jedisPool = new JedisSentinelPool(master,set,config,200,password,2);
        return jedisPool;
    }
}
