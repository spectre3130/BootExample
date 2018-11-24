package com.spectre.example.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    private final static Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
//
//    @RabbitListener(queues = "${myqueue}")
//    public void handler(String message) {
//        logger.info("소비기 > " + message);
//    }
}
