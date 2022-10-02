package com.chatapp.app.service;

import com.chatapp.app.model.Message;
import com.chatapp.app.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService{
    @Autowired
    private IMessageRepository messageRepository;

    /**
     *
     * @param user1Id
     * @param user2Id
     * @return Message
     */
    @Override
    public List<Message> getAllMessagesByConversation(Integer user1Id, Integer user2Id) {
        return messageRepository.getMessagesByConversation(user1Id, user2Id);
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
