package demo.springframework.activemq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {
	
    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    /*
     * NOTE- @EnableJms will configure a DefaultJmsListenerContainerFactory (i.e. a class 
     * listening for incoming JMS messages and routing them to the appropriate MDP (MessageDriven
     * Pojo)
     * 
     * If bespoke functionality is required a JmsListenerContainerFactory bean can be explicitly
     * declared in @Configuration and the relevant configuration settings can be overriden
     * 
     * Note also that SpringBoot intgegration tests use an embedded ApacheMQ instance
     * In practice the MQ instance(s) to be used would typically be configured via application.properties
     * (with the embedded option explicitly disabled and the appropriate addressing properties given instead)
    @Bean
    public DefaultJmsListenerContainerFactory myJmsListenerContainerFactory() {
      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
      factory.setSessionTransacted(true);
      factory.setConcurrency("5");
      return factory;
    }
    */

}
