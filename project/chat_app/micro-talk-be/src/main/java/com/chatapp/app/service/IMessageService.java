package com.chatapp.app.service;

import com.chatapp.app.model.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getAllMessagesByConversation(Integer user1Id, Integer user2Id);
    Message saveMessage(Message message);
}
