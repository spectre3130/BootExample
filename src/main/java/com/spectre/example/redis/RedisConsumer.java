package com.spectre.example.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisConsumer {

    private final static Logger logger = LoggerFactory.getLogger(RedisConsumer.class);

    public void messageHandler(String message) {
        logger.info("소비기 > " + message);
    }
}
