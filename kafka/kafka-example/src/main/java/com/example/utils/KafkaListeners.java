package com.example.utils;

import com.example.model.Message;
import com.example.model.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "chatApp", topics = "${spring.kafka.topic-1}")
public class KafkaListeners {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaHandler
    void handlerListener(String data) {
        System.out.println("Listener received: " + data);
    }

    @KafkaHandler
    void handlerMessageRequestListener(Message message) {
        System.out.println("Listener received: " + message);
        template.convertAndSend("/topic/group", message);
    }
}
