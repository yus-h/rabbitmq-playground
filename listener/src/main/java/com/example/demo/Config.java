package com.example.demo;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

    // Define the Queue to listen to, and the exchange
    private static final String MY_QUEUE = "Queue1";
    private static final String MY_EXCHANGE = "Exchange1";
    private static final String MY_TOPIC = "Topic1";
    private static final String CONNETION_USER = "guest";
    private static final String CONNECTION_PASSWORD = "guest";

    @Bean
    Queue myQueue() {
        return new Queue(MY_QUEUE, true);
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.topicExchange(MY_EXCHANGE)
                .durable(true)
                .build();
    }

    // Bind queue to exchange
    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with(MY_TOPIC)
                .noargs();
    }


    // Provide the connection to the Queue
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory= new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername(CONNETION_USER);
        cachingConnectionFactory.setPassword(CONNECTION_PASSWORD);
        return cachingConnectionFactory;
    }


    // Bind the queue, connection and listener
    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new Listener());
        return simpleMessageListenerContainer;
    }




}
