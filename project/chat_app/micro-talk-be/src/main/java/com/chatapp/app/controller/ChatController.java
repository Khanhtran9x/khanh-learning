package com.chatapp.app.controller;

import com.chatapp.app.constants.KafkaConstants;
import com.chatapp.app.model.Customer;
import com.chatapp.app.model.Message;
import com.chatapp.app.model.User;
import com.chatapp.app.service.ICustomerService;
import com.chatapp.app.service.IUserService;
import com.chatapp.app.service.MessageService;
import com.chatapp.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private MessageService messageService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(Pageable pageable) {
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessagesByConversation(@RequestParam Integer senderId, @RequestParam Integer receiverId) {
        List<Message> messages = messageService.getAllMessagesByConversation(senderId, receiverId);
        if (messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.out.println("messages");
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            System.out.println("received msg from fe");
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    //    -------------- WebSocket API ----------------
    @MessageMapping("/chats")
    @SendTo("/topic/chats")
    public Message broadcastGroupMessage(@Payload Message message) {
        //Sending this message to all the subscribers
        return message;
    }

//    @MessageMapping("/newUser")
//    @SendTo("/topic/chats")
//    public Message addUser(@Payload Message message,
//                           SimpMessageHeaderAccessor headerAccessor) {
//        // Add user in web socket session
//        headerAccessor.getSessionAttributes().put("username", message.getSender());
//        return message;
//    }

}
