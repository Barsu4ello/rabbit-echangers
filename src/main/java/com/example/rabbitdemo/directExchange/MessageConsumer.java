package com.example.rabbitdemo.directExchange;

import com.example.rabbitdemo.config.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("consumer")
@Service
public class MessageConsumer {

    @RabbitListener(queues = "demo.queue.one")
    public void receiveFromQueueOne(User user) {
        System.out.println(" [Queue One] Received user: " + user);
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