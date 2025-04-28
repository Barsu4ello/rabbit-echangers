package com.example.rabbitdemo.fanoutExchange;

import com.example.rabbitdemo.config.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("producer")
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend("fanoutExchange", "", user);
        System.out.println(" [x] Sent '" + user + "'");
    }
}