package com.visu.jms.springactivemqpublishersubscriber.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class PublisherConfig {
    private ActiveMQConnectionFactory activeMQConnectionFactory;
    public PublisherConfig(@Autowired ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.activeMQConnectionFactory =  activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        return new CachingConnectionFactory(activeMQConnectionFactory);
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}
