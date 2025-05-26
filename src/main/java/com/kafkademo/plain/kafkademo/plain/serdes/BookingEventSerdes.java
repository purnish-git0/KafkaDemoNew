package com.kafkademo.plain.kafkademo.plain.serdes;

import com.kafkademo.plain.kafkademo.plain.event.BookingEvent;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

public class BookingEventSerdes {



    public static Serde<BookingEvent> serdes() {
        Map<String, Object> serdeProps = new HashMap<>();

        final Serializer<BookingEvent> bookingEventSerializer = new JsonSerializer<>();
        bookingEventSerializer.configure(serdeProps, false);

        final Deserializer<BookingEvent> bookingEventDeserializer = new JsonDeserializer<>();
        return Serdes.serdeFrom(bookingEventSerializer, bookingEventDeserializer);

    }
}
