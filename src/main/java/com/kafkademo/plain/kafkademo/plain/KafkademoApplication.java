package com.kafkademo.plain.kafkademo.plain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafka
public class KafkademoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkademoApplication.class, args);
    }

}
