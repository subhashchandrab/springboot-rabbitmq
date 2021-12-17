package test.oci.sb.rabbit.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.oci.sb.rabbit.model.RabbitMessage;
import com.oci.sb.rabbit.service.RabbitMQReceiver;

public class RabbitMQReceiverTest {
	
	@Autowired
	private RabbitMQReceiver rabbitMqReceiver;
	
	@Test
	public void shouldPrintTheReceivedMessage() throws Exception {
		rabbitMqReceiver.receive(new RabbitMessage(100,"Message100"));
	}

}
