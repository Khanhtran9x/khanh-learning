package com.example.demorabbitmq.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private String orderId;
    private String name;
    private int qty;
    private double price;
}
