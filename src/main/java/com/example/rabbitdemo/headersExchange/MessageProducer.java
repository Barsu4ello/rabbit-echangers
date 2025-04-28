package com.example.rabbitdemo.headersExchange;

import com.example.rabbitdemo.config.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("producer")
@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        MessageProperties messageProperties = new MessageProperties();

        // Устанавливаем нужные заголовки
//        messageProperties.setHeader("testheader", "123");
        messageProperties.setHeader("testheader2", "1234");

        // Сериализуем объект User в JSON автоматически
        MessageConverter converter = rabbitTemplate.getMessageConverter();
        Message message = converter.toMessage(user, messageProperties);

        rabbitTemplate.send("headersExchange", "", message);

        System.out.println(" [x] Sent '" + user + "'");
    }
}