package com.visu.jms.springactivemqpublishersubscriber.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class SubscriberConfig {
    private ActiveMQConnectionFactory activeMQConnectionFactory;

    public SubscriberConfig(@Autowired ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.activeMQConnectionFactory = activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory);
        jmsListenerContainerFactory.setPubSubDomain(true);
        return jmsListenerContainerFactory;
    }
}
