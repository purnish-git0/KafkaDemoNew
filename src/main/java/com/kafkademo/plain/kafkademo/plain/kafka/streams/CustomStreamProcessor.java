package com.kafkademo.plain.kafkademo.plain.kafka.streams;

import com.kafkademo.plain.kafkademo.plain.kafka.BookableEvent;
import com.kafkademo.plain.kafkademo.plain.kafka.BookingEventInfo;
import com.kafkademo.plain.kafkademo.plain.kafka.EventBooking;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.ProcessBuilder.Redirect.to;

@Component
public class CustomStreamProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void process(StreamsBuilder kStreamBuilder) {

        KStream<String, String> exampleStream = kStreamBuilder.stream("demo-topic-1", Consumed.with(STRING_SERDE, STRING_SERDE));

        exampleStream.mapValues((key,value) -> {
            return value+"_mapped";
        }).to("demo-topic-2");


        

    }
}

