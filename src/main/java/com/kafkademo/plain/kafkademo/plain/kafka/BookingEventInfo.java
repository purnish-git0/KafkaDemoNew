package com.kafkademo.plain.kafkademo.plain.kafka;

public class BookingEventInfo {

    private BookableEvent bookableEvent;

    private EventBooking eventBooking;


    public BookingEventInfo(EventBooking eventBooking, BookableEvent bookableEvent) {
        this.eventBooking = eventBooking;
        this.bookableEvent = bookableEvent;
    }
}
