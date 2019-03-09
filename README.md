## About

Playing around with Rabbit MQ

Sender: responsible for sending messages to the queue
Listener: responsible for listening for messages from the queue


## To run the application:

* Follow instructions to install and start rabbitmq	(https://www.rabbitmq.com/download.html)
* You can view the RabbitMQ Management UI at http://localhost:15672. Default user and password is "guest"


1. Listener
* mvn spring-boot:run 
This will create the queue/exchange. You can verify this has worked by viewing the RabbitMQ Management.

2. Sender
* mvn spring-boot:run
The custom message sent is defined in SenderApplication.java
Upon running the application, you should see the message receieved in the command line window for the Listener application.