package com.spectre.example.config;

import com.spectre.example.rabbitmq.RabbitMQProducer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableRabbit
public class RabbitMQConfig {

    @Value("${myqueue}")
    private String queue;

    private final RabbitMQProducer rabbitMQProducer;

    public RabbitMQConfig(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

//    @Scheduled(fixedDelay = 3000L)
//    public void sendMessage() {
//        producer.sendTo(queue, "안녕하세요, 여러분! 지금 시각은" + new Date());
//    }
}
