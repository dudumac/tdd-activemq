package demo.springframework.activemq.model;

import lombok.Data;

@Data
public class CvimMessage {
	private String sensorName;
	private double sensorValue;
}
