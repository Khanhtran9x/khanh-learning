package com.chatapp.app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
    @Column(columnDefinition = "DATE")
    private String customerDateOfBirth;
    private String customerAvatarUrl;
    private String customerPhoneNumber;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
