package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic-1}")
    private String topic1;
    @Value("${spring.kafka.topic-2}")
    private String topic2;

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(topic1).build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name(topic2).build();
    }
}
