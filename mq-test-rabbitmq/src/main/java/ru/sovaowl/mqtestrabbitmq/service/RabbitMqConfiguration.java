package ru.sovaowl.mqtestrabbitmq.service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class RabbitMqConfiguration {

    public static final String KEY_QUEUE = "test-q";
    public static final String KEY_EXCHANGE = "test-q";
    public static final String KEY_ROUTING = "routingKey";

    @Bean
    public Queue queue() {
        return new Queue(KEY_QUEUE, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(KEY_EXCHANGE, false, false);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(KEY_ROUTING);
    }
}
