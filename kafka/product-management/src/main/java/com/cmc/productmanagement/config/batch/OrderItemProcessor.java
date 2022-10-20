package com.cmc.productmanagement.config.batch;

import com.cmc.productmanagement.entity.OrderEntity;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.springframework.batch.item.ItemProcessor;
@Component
@StepScope
public class OrderItemProcessor implements ItemProcessor<OrderEntity, OrderEntity> {

    @Override
    public OrderEntity process(OrderEntity data) throws Exception {
        System.out.println("data received from reader");
        System.out.println(data.getProductName());
        return data;
    }
}
