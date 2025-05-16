package com.kafkademo.plain.kafkademo.plain.kafka;

import lombok.Data;

@Data
public class BookableEvent {

    private Integer eventId;

    private String duration;


}
