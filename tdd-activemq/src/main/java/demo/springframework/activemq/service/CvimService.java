package demo.springframework.activemq.service;

import org.springframework.stereotype.Service;

import demo.springframework.activemq.model.CvimMessage;

@Service
public class CvimService {
	private CvimMessage cvimMessage;
	
	public void processCvimMessage(CvimMessage cvimMessage) {		
		this.cvimMessage = cvimMessage;
	}

	public CvimMessage getCvimMessage() {
		return cvimMessage;
	}
}
