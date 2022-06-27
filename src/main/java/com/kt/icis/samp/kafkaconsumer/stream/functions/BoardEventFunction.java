package com.kt.icis.samp.kafkaconsumer.stream.functions;

import java.util.function.Consumer;

import com.kt.icis.samp.kafkaconsumer.events.BoardCreateEvent;
import com.kt.icis.samp.kafkaconsumer.events.BoardDeleteEvent;
import com.kt.icis.samp.kafkaconsumer.events.BoardUpdateEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardEventFunction {

    private Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();

    @Bean
    public Consumer<Message<String>> boardCreate(){
        return ((msg) -> {
            log.info("boardCreate 이벤트 수신: {}",msg); 
            try {
                BoardCreateEvent event = mapper.fromJson(msg.getPayload(),BoardCreateEvent.class );
                log.info("==============[Consumer]============BoardCreateEvent: {}", event.toString());
                
                repositoryBoardCreate(event);
                
                Acknowledgment acknowledgment = msg.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);           
                if(acknowledgment != null){
                    log.info("==============[Consumer]===Acknowledgment provided");            
                    acknowledgment.acknowledge();
                }             
            } catch (Exception e) {             
                e.printStackTrace();
            }
        });
    }

    @Bean
    public Consumer<Message<String>> boardUpdate(){
        return ((msg) -> {
            log.info("boardUpdate 이벤트 수신: {}",msg); 
            try {
                BoardUpdateEvent event = mapper.fromJson(msg.getPayload(),BoardUpdateEvent.class );
                log.info("=============[Consumer]===================BoardUpdateEvent: {}", event.toString());
                //repositoryBoardUpdate(event);
                
            } catch (Exception e) {             
                e.printStackTrace();
            }

        });
    }
    @Bean
    public Consumer<Message<String>> boardDelete(){
        return ((msg) -> {
            log.info("boardDelete 이벤트 수신: {}",msg); 
            try {
                BoardDeleteEvent event = mapper.fromJson(msg.getPayload(),BoardDeleteEvent.class );
                log.info("==============[Consumer]==================BoardDeleteEvent: {}", event.toString());
                //repositoryBoardDelete(event);
            } catch (Exception e) {             
                e.printStackTrace();
            }
        });

    }

    private void repositoryBoardCreate(BoardCreateEvent event) {        
        log.info("======== BoardCreateEvent : {}", event.toString());
    }

    private void repositoryBoardUpdate(BoardUpdateEvent event){
        log.info("======== BoardUpdateEvent : {}", event.toString());
        
    }
    private void repositoryBoardDelete(BoardDeleteEvent event) {
        log.info("======== BoardDeleteEvent : {}", event.toString());
    }

    
}
