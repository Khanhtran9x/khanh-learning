package com.chatapp.app.repository;

import com.chatapp.app.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = "SELECT * FROM message " +
            "WHERE (sender_id = :user_1_id and receiver_id = :user_2_id) " +
            "or (sender_id = :user_2_id and receiver_id = :user_1_id)", nativeQuery = true)
    List<Message> getMessagesByConversation(@Param("user_1_id") Integer user1Id,
                                            @Param("user_2_id") Integer user2Id);
}
