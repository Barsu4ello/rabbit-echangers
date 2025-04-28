package com.example.rabbitdemo.defaultExchange;

import com.example.rabbitdemo.config.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@Profile("producer")
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageProducer producer;

    @PostMapping
    public String sendMessage(@RequestBody User user) {
        producer.sendMessage(user);
        return "Message sent: " + user;
    }
}