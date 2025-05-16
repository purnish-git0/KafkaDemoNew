package com.kafkademo.plain.kafkademo.plain.executory;

import com.kafkademo.plain.kafkademo.plain.kafka.producer.BookingProducer;
import com.kafkademo.plain.kafkademo.plain.kafka.producer.CustomPartitionProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyExecutor {

    private final BookingProducer producer;

    private final CustomPartitionProducer customPartitionProducer;


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
