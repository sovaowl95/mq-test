package ru.sovaowl.mqtestrabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();


    @RabbitListener(queues = RabbitMqConfiguration.KEY_QUEUE)
    public void listen(Message message) {
        System.out.println("consumed...");
    }
}
