package demo.springframework.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import demo.springframework.activemq.model.CvimMessage;
import demo.springframework.activemq.service.CvimService;

@Component
public class MessageDrivenPojo {

	@Autowired
	CvimService cvimService;
	
	@JmsListener (destination = "cvim.telemetry")
    public void receiveMessage(CvimMessage cvimMessage) {
        cvimService.processCvimMessage(cvimMessage);
    }
}
