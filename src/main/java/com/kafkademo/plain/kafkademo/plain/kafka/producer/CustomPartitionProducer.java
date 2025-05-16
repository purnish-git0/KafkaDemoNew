package com.kafkademo.plain.kafkademo.plain.kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class CustomPartitionProducer {

    KafkaTemplate<String, String> customPartitionKafkaTemplate;

    public void publishToPartitions(String topic, String key, String message) {
        topic = "custom-topic-3";

        CompletableFuture<SendResult<String, String>> newFuture = customPartitionKafkaTemplate.send(topic, key
        ,message);
        newFuture.whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println(result.toString());
                    } else {
                        System.out.println("error");
                    }
                }
        );
    }
}
