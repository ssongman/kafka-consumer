package com.kt.icis.samp.kafkaconsumer.events;

import java.time.LocalDateTime;

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
public class BoardUpdateEvent {
    private String eventName;
    private int num;
    private String title;
    private String contents; 
	private String modifyId;
    private String modifyName;	 	 
	private LocalDateTime  modifyDate;
    

}
