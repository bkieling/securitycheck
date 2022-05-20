package com.example.springboot;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue rfaUploadedQueue() {
        return new Queue("rfa.uploaded");
    }

//    @Bean
//    public FanoutExchange fanout() {
//        return new FanoutExchange("rfa.fanout");
//    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public Binding binding(FanoutExchange fanout, Queue rfaUploadedQueue) {
//        return BindingBuilder.bind(rfaUploadedQueue).to(fanout);
//    }

}