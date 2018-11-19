package com.spectre.example.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisProducer {

    private final static Logger logger = LoggerFactory.getLogger(RedisProducer.class);

    private final StringRedisTemplate template;

    public RedisProducer(StringRedisTemplate template) {
        this.template = template;
    }

    public void sendTo(String topic, String message) {
        logger.info("전송 > ...");
        this.template.convertAndSend(topic, message);
    }
}
