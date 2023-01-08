package com.example.demorabbitmq.publisher;

import com.example.demorabbitmq.config.MessageConfig;
import com.example.demorabbitmq.dto.Order;
import com.example.demorabbitmq.dto.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderPublisher {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/{restaurantName}")
    public String orderBook(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in " + restaurantName);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.KEY, orderStatus);
        return "success";
    }
}
