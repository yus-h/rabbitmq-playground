package com.example.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class Listener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        System.out.println("Received message: " + new String(message.getBody()));
    }
}
