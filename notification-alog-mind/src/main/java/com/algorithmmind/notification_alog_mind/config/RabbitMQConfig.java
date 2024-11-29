package com.algorithmmind.notification_alog_mind.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class RabbitMQConfig {


    @Value("${algo.mind.exchange.name}")
    private String mailExchange;

    @Value("${algo.mind.queue.name}")
    private String mailQueue;

    @Value("${algo.mind.routing.key}")
    private String mailRoutingKey;


    @Bean
    public DirectExchange directExchange() {
        log.info("exchange bean getting created with name: {}",mailExchange);
        return new DirectExchange(mailExchange);
    }


    @Bean
    public Queue emailQueue() {
        log.info("mail bean getting created with name: {}", mailQueue);
        return QueueBuilder
                .durable(mailQueue)
                .build();
    }

    @Bean
    public Binding binding(DirectExchange directExchange,
                           Queue queue) {

        log.info("binding bean getting created b/w exchange {} and Queue {} ",directExchange.getName(), queue.getName());
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(mailRoutingKey);
    }


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
