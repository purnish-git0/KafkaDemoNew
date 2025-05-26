package com.kafkademo.plain.kafkademo.plain.executory;

import com.kafkademo.plain.kafkademo.plain.kafka.producer.BookingProducer;
import com.kafkademo.plain.kafkademo.plain.kafka.producer.CustomPartitionProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class MyExecutor {

    private final BookingProducer producer;

    private final CustomPartitionProducer customPartitionProducer;

    private final StreamsBuilderFactoryBean factoryBean;


    public void startStreamProcessing() {
        producer.pushToStreamingTopic("example");

    }

    public void startOps() {
        int ctr=0;
        while(ctr<10){
            producer.publishToTopic("Message no:" + ctr);
            ctr++;
        }
    }
    public void startPublishing() {
        int ctr = 0;
        while(ctr <10) {
            customPartitionProducer.publishToPartitions("custom-topic-3", "" + ctr,
                    "message_"+ctr);
            ctr++;
        }
    }
}
