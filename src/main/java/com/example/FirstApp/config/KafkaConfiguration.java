package com.example.FirstApp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {
    public NewTopic javaguidesTopic(){
        return TopicBuilder.name("javaguides").build();
    }
}
