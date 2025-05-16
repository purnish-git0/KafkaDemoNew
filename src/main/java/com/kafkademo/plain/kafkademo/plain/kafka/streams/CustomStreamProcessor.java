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

@Component
public class CustomStreamProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    private static final Serde<EventBooking> EVENT_BOOKING_SERDE = new JsonSerde<>(EventBooking.class);

    private static final Serde<BookableEvent> BOOKABLE_EVENT_SERDE = new JsonSerde<>(BookableEvent.class);

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> customTopic2Stream = streamsBuilder
                .stream("custom-topic-2", Consumed.with(STRING_SERDE, STRING_SERDE));

        KTable<String, String> kTableTopic2 = customTopic2Stream.toTable();

        KStream<String, BookableEvent> bookableEventKStream = streamsBuilder
                .stream("bookable-event-topic-2", Consumed.with(STRING_SERDE, BOOKABLE_EVENT_SERDE));

        KTable<String, BookableEvent> kTableBookableEvent = bookableEventKStream.toTable();



        KStream<String, EventBooking> bookingStream = streamsBuilder
                .stream("event-booking-topic-3", Consumed.with(STRING_SERDE, EVENT_BOOKING_SERDE));
//
//        KeyValueMapper<EventBooking, BookableEvent, BookingEventInfo> foreignKeyExtractor =
//                (eventBooking, bookableEvent) -> eventBooking.get("id").toString();
//
//
//
//        KStream<String, BookingEventInfo> bookingEventInfoKStream
//                = bookableEventKStream.leftJoin()


    }
}

