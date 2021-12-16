package com.oci.sb.rabbit.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oci.sb.rabbit.model.RabbitMessage;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${oci.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${oci.rabbitmq.routingkey}")
	private String routingkey;	
	String kafkaTopic = "java_in_use_topic";
	
	public void send(RabbitMessage message) {
		System.out.println("Using exchange: "+ exchange + ", " + "routingKey: " + routingkey);
		amqpTemplate.convertAndSend(exchange, routingkey, message);
		System.out.println("Sent message = " + message);
	    
	}
}