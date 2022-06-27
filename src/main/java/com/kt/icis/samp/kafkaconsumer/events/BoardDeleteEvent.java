package com.kt.icis.samp.kafkaconsumer.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class BoardDeleteEvent {
    private String eventName;
    private int num;

}
