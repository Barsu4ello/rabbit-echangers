package com.example.rabbitdemo.replyTo;

import com.example.rabbitdemo.config.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Profile("producer")
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {

        rabbitTemplate.setReplyTimeout(10000);
        User userResponse = (User)rabbitTemplate.convertSendAndReceive("demo.queue.one", user);

        System.out.println(userResponse);
    }
}