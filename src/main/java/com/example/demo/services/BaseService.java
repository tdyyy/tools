package com.example.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.io.InputStream;
import java.util.Map;

@Service
@Slf4j
public class BaseService {
    private Map yamlData;
    @Autowired
    private JedisSentinelPool pool;

    public void RulesReader(InputStream inputStream) {
        Yaml yaml = new Yaml(new SafeConstructor());
        yamlData = (Map) yaml.load(inputStream);
    }

    public String hello(String str){
        return innerHello()+str;
    }

    public String innerHello(){
        log.info("xxxxxxxxxxxxxxx");
        return "innerHello";
    }

    public static String outerHello(String str){
        log.info("yyyyyyyyyyy");
        return "outerHello";
    }

    public String redisTest(String key){
        Jedis jedis = pool.getResource();
        Long task_queue_test = jedis.lpush(key, "{\"createTime\":1651746209172,\"unit_id\":\"262\",\"his_dep_id\":\"401003\",\"start_date\":\"2022-05-07\",\"end_date\":\"2022-05-07\",\"taskType\":2}");
//            Long task_queue_test = jedis.lpush("TASK_QUEUE_TEST", "{\"createTime\":1651746209172,\"unit_id\":\"200029701\",\"his_dep_id\":\"10010015\",\"start_date\":\"2022-06-07\",\"end_date\":\"2022-06-07\",\"taskType\":2}");
        jedis.close();
        return "1";
    }
}
