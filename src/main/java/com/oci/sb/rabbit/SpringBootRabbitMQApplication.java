package com.oci.sb.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oci.sb.rabbit.model.RabbitMessage;
import com.oci.sb.rabbit.service.RabbitMQSender;

@SpringBootApplication(scanBasePackages = { "com.oci.sb.rabbit" })
@RestController
@RequestMapping(value = "/oci-rabbitmq/")
@EnableRabbit
public class SpringBootRabbitMQApplication {

	@Autowired
	RabbitMQSender rabbitMQSender;
	record Point(long x, long y) { }
	
	private static void foo(Object o) {
		   switch (o) {
		     case Integer i     -> System.out.println("String:");
		     case String s     	-> System.out.println("String: Hello World!");
		     default       	-> System.out.println("Object");
		   }
		}
	
	public static void main(String[] args) {

		SpringApplication.run( SpringBootRabbitMQApplication.class , args);
	}

	@RequestMapping("/")
	public String home() {
	    String textBlockFootballers = """
	            Footballers
	              with double space indentation
	                and "SW TEST ACADEMY TEAM" Rocks!
	            """;		 
		return "<h1>SpringBoot demo application with RabbitMQ integration</h1>";
	}

	@RequestMapping("/producer")
	public String producer(@RequestParam("id") Integer id, @RequestParam("content") String content) {
		//
		try {
			RabbitMessage msg = new RabbitMessage(id,content);
			msg.setId(id);
			msg.setContent(content);
			rabbitMQSender.send(msg);
			return "Message sent to the RabbitMQ  Successfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error occurred while sending the message to RabbitMQ";
		}

		
	}
}
