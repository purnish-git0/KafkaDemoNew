package com.kafkademo.plain.kafkademo.plain.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
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

//    kafkaCustomTopicListenerContainerFactory
    @KafkaListener(
            topicPartitions =
            @TopicPartition(topic = "custom-topic-3", partitions = { "0", "1" }),
            topics = "custom-topic-3", groupId = "custom-topic-consumer-group", containerFactory = "kafkaCustomTopicListenerContainerFactory")
    public void listenMessageFromCustomTopic(ConsumerRecord<?, ?> consumerRecord) {
        LOG.info(consumerRecord.topic() + " " + consumerRecord.partition());
    }

    @KafkaListener(topicPartitions =
    @TopicPartition(topic = "custom-topic-3", partitions = { "2", "3" }),
            topics = "custom-topic-3", groupId = "custom-topic-consumer-group", containerFactory = "kafkaCustomTopicListenerContainerFactory")
    public void listenMessageFromCustomTopic2(ConsumerRecord<?, ?> consumerRecord) {
        LOG.info(consumerRecord.topic() + " " + consumerRecord.partition());
    }

    @KafkaListener(topicPartitions =
    @TopicPartition(topic = "custom-topic-3", partitions = { "4", "5", "6", "0" }),
            topics = "custom-topic-3", groupId = "custom-topic-consumer-group", containerFactory = "kafkaCustomTopicListenerContainerFactory")
    public void listenMessageFromCustomTopic3(ConsumerRecord<?, ?> consumerRecord) {
        LOG.info(consumerRecord.topic() + " " + consumerRecord.partition());
    }

    @KafkaListener(topics = "custom-topic-3", groupId = "custom-topic-consumer-group", containerFactory = "kafkaCustomTopicListenerContainerFactory")
    public void listenMessageFromCustomTopic4(ConsumerRecord<?, ?> consumerRecord) {
        LOG.info(consumerRecord.topic() + " " + consumerRecord.partition());
    }

    
}