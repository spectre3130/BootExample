package com.spectre.example.config;


import com.spectre.example.redis.RedisConsumer;
import com.spectre.example.redis.RedisProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Value("${topic}")
    private String topic;

//    @Bean
//    public RedisMessageListenerContainer container(
//            RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic(topic));
//        return container;
//    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisConsumer redisConsumer) {
        return new MessageListenerAdapter(redisConsumer, "messageHandler");
    }

//    @Bean
//    CommandLineRunner sendMessage(RedisProducer redisProducer) {
//        return args -> {
//            redisProducer.sendTo(topic, "레디스 시작");
//        };
//    }
}
