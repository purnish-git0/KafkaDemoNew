package com.kafkademo.plain.kafkademo.plain.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
public class BookingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishToTopic(String message) {
        String topicName = "message-from-user-event";
        CompletableFuture<SendResult<String, String>> newFuture = kafkaTemplate.send(topicName, message);
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
