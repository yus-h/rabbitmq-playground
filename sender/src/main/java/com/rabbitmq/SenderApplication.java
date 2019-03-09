package com.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SenderApplication implements CommandLineRunner {

	private static final String MY_EXCHANGE = "Exchange1";
	private static final String MY_TOPIC = "Topic1";


	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {

		SpringApplication.run(SenderApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		SimpleMessage simpleMessage = new SimpleMessage();
		simpleMessage.setName("Message name");
		simpleMessage.setDescription("This is the description for the message!");
		rabbitTemplate.convertAndSend(MY_EXCHANGE, MY_TOPIC, simpleMessage);
	}
}

