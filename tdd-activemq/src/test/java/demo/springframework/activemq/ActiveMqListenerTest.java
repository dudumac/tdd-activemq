package demo.springframework.activemq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import demo.springframework.activemq.model.CvimMessage;
import demo.springframework.activemq.service.CvimService;


// NOTE - For JUnit4 @RunWith(SpringRunner.class) must be included in order to pick up @Configuration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqListenerTest {

	@Autowired
	CvimService cvimService;
	
	@Autowired 
	JmsTemplate jmsTemplate;
	
	@Test
	public void testMessageDrivenPojoReceivesRequest() throws InterruptedException {
		CvimMessage testTelemetry = new CvimMessage();
		testTelemetry.setSensorName("testSensor");
		testTelemetry.setSensorValue(100.0);
		jmsTemplate.convertAndSend("cvim.telemetry", testTelemetry);
		
		Optional<CvimMessage> receivedMessage = pollCvimServiceForJmsMessage();
		assertTrue(receivedMessage.isPresent());
		assertEquals(testTelemetry, receivedMessage.get());
	}

	private Optional<CvimMessage> pollCvimServiceForJmsMessage() throws InterruptedException {
		Optional<CvimMessage> receivedMessage = Optional.empty();
		
		for(int i = 0; i<5; i++) {
			receivedMessage = Optional.ofNullable(cvimService.getCvimMessage());
			if (receivedMessage.isPresent()) {
				return receivedMessage;
			}
			Thread.sleep(500);
		}
		return receivedMessage;
		
	}
}
