package com.oci.sb;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oci.sb.postgre.service.PostgreDataService;
import com.oci.sb.postgre.model.PostgreUser;
import com.oci.sb.rabbit.model.RabbitMessage;
import com.oci.sb.rabbit.service.RabbitMQSender;

@SpringBootApplication(scanBasePackages = { "com.oci.sb" })
@RestController
@RequestMapping(value = "/oci-sb/")
@EnableRabbit
public class SpringBootRabbitMQApplication {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@Autowired
	PostgreDataService postgreService;

	record Point(long x, long y) {
	}

	private static void foo(Object o) {
		switch (o) {
		case Integer i -> System.out.println("String:");
		case String s -> System.out.println("String: Hello World!");
		default -> System.out.println("Object");
		}
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootRabbitMQApplication.class, args);
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

	@RequestMapping("/sendRabbitMessage")
	public String producer(@RequestParam("id") Integer id, @RequestParam("content") String content) {
		//
		try {
			RabbitMessage msg = new RabbitMessage(id, content);
			rabbitMQSender.send(msg);
			return "Message sent to the RabbitMQ  Successfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error occurred while sending the message to RabbitMQ";
		}

	}

	@RequestMapping("/addPostgreUser")
	public String updatePostgreData(@RequestParam("id") Integer id, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("country") String country,
			@RequestParam("password") String password) {
		try {
			PostgreUser user = new PostgreUser(id, name, email, country, password);
			postgreService.addUser(user);
			return "The details of the user '" +  name + "' added to the Postgre succesfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error occurred while updating the Postgre database";
		}
	}
}
