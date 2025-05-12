package com.kafkademo.plain.kafkademo.plain.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class KafkaConsumer {

    private Logger LOG = Logger.getAnonymousLogger();


    @KafkaListener(topics = "booking-event", groupId = "booking-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenBookingEvents(String message) {
        LOG.info(message);
    }

    @KafkaListener(topics = "message-from-user-event", groupId = "booking-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenMessageFromUser(ConsumerRecord<?, ?> consumerRecord) {
        LOG.info(consumerRecord.topic() + " " + consumerRecord.partition());
    }

    @KafkaListener(topics = "cab-reservation", groupId = "cab-booking-group", containerFactory = "kafkaListenerContainerFactory")
    public void listenMessageFromCabUser(ConsumerRecord<?, ?> consumerRecord) {
        LOG.info(consumerRecord.topic() + " " + consumerRecord.partition());
    }

}