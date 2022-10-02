package com.chatapp.app.consumer;

import com.chatapp.app.constants.KafkaConstants;
import com.chatapp.app.model.Message;
import com.chatapp.app.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    IMessageService messageService;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        System.out.println(message.getContent());
        messageService.saveMessage(message);
        template.convertAndSend("/topic/chats", message);
    }
}
