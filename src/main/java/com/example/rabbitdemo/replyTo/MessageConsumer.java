package com.example.rabbitdemo.replyTo;

import com.example.rabbitdemo.config.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Profile("consumer")
@Service
@RequiredArgsConstructor
public class MessageConsumer {

    @RabbitListener(queues = "demo.queue.one")
    public User processRequest(User user) {

        if (user.getName().equals("Vlad")) {
            user.setName("Dodster");
        }
        return user;
    }

    @RabbitListener(queues = "demo.queue.two")
    public void receiveFromQueueTwo(User user) {
        System.out.println(" [Queue Two] Received user: " + user);
    }

    @RabbitListener(queues = "demo.queue.three")
    public void receiveFromQueueThree(User user) {
        System.out.println(" [Queue Three] Received user: " + user);
    }
}