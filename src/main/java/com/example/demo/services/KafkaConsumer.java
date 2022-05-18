package com.example.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
//    @KafkaListener(topics = "DOCTOR_SCH_SUCCESS_1")
    public void consumer(@Payload String foo,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts){
        log.info("kafka consumer start");
        log.info("kafka topic:{}",topic);
        log.info("kafka msg:{}",foo);
        log.info("kafka timestamp:{}",ts);
        log.info("concurrent timestamp:{}",System.currentTimeMillis());
    }

//    @KafkaListener(topics = "DOCTOR_SCH_SUCCESS_HIS")
    public void consumer2(@Payload String foo,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts){
        log.info("kafka consumer2 start");
        log.info("kafka topic:{}",topic);
        log.info("kafka msg:{}",foo);
        log.info("kafka timestamp:{}",ts);
        log.info("concurrent timestamp:{}",System.currentTimeMillis());
    }
}
