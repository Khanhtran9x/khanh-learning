package com.cmc.productmanagement.controller;

import com.cmc.productmanagement.constants.KafkaConstants;
import com.cmc.productmanagement.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private KafkaTemplate<String, OrderEntity> kafkaTemplate;

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody OrderEntity orderEntity) {
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, orderEntity).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
