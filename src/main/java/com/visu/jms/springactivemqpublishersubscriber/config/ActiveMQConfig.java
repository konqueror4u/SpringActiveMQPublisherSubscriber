package com.visu.jms.springactivemqpublishersubscriber.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Topic;

@Configuration
@EnableJms
public class ActiveMQConfig {
    @Value("${activemq.broker.url}")
    public String brokerUrl;

    @Value("${activemq.topic}")
    public String topic;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        return activeMQConnectionFactory;
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topic);
    }
}
