package demo.springframework.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class TddActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(TddActivemqApplication.class, args);
	}
}
