package com.kafkademo.plain.kafkademo.plain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEvent {


    private Long bookingId;

    private String name;
}
