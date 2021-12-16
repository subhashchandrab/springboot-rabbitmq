package com.oci.sb.rabbit.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
    @RabbitListener(queues = {"${oci.rabbitmq.queue}"})
    public void receive(@Payload Object fileBody) {
        System.out.println("Received Message " + fileBody);
    }
}
