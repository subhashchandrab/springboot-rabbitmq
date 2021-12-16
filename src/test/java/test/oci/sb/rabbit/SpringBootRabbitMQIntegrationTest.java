package test.oci.sb.rabbit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.oci.sb.rabbit.SpringBootRabbitMQApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRabbitMQApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class SpringBootRabbitMQIntegrationTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testApplicationConnectivity() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/oci-rabbitmq/"),
				HttpMethod.GET, entity, String.class);

		String expected = "<h1>SpringBoot demo application with RabbitMQ integration</h1>";
		System.out.println("Body:" + response.getBody());
		Assert.assertEquals(expected, response.getBody());
	}
	
	@Test
	public void testRabbitMQConnectivity() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/oci-rabbitmq/producer?id=100&content='Sample Message'"),
				HttpMethod.GET, entity, String.class);

		String expected = "Message sent to the RabbitMQ  Successfully";
		Assert.assertEquals(expected, response.getBody());
	}
	

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
