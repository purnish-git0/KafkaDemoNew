package com.kafkademo.plain.kafkademo.plain.executory;

import com.kafkademo.plain.kafkademo.plain.kafka.producer.BookingProducer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MyExecutor {

    private final BookingProducer producer;


    public void startOps() {
        int ctr=0;
        while(ctr<10){
            producer.publishToTopic("Message no:" + ctr);
            ctr++;
        }
    }
}
