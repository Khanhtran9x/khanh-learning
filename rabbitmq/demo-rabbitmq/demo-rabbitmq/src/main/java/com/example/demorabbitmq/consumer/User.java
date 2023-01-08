package com.example.demorabbitmq.consumer;

import com.example.demorabbitmq.config.MessageConfig;
import com.example.demorabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message from queue: " + orderStatus.getOrder().getOrderId());
    }
}
