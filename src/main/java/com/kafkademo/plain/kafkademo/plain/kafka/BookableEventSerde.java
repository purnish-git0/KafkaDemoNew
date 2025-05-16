package com.kafkademo.plain.kafkademo.plain.kafka;

import org.springframework.kafka.support.serializer.JsonSerde;

public class BookableEventSerde extends JsonSerde<BookableEvent> {
}
