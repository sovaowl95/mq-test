package ru.sovaowl.mqtestrabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    @Scheduled(fixedRate = 1000)
    public void produce() {
        final int messageNum = random.nextInt(10_000);
        System.out.println("producing " + messageNum + " messages...");

        UUID lastUuid = UUID.randomUUID();

        for (int i = 0; i < messageNum; i++) {
            byte[] text = ("Message " + i).getBytes();
            final MessageProperties messageProperties = new MessageProperties();
            final UUID randomUUID = UUID.randomUUID();
            messageProperties.setMessageId(randomUUID.toString());
            messageProperties.setCorrelationId(lastUuid.toString());


            final Message message = new Message(text, messageProperties);
            rabbitTemplate.convertAndSend(
                    RabbitMqConfiguration.KEY_EXCHANGE,
                    RabbitMqConfiguration.KEY_ROUTING,
                    message
            );
        }
    }
}
