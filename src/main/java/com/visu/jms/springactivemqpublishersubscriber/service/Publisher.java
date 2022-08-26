package com.visu.jms.springactivemqpublishersubscriber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visu.jms.springactivemqpublishersubscriber.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Topic;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/subscribe")
public class Publisher {

    private JmsTemplate jmsTemplate;
    private Topic topic;
    public Publisher(@Autowired JmsTemplate jmsTemplate, @Autowired Topic topic){
        this.jmsTemplate = jmsTemplate;
        this.topic = topic;
    }

    @PostMapping("/message")
    public Order sendMessage(@RequestBody Order order){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String orderAsJson =  objectMapper.writeValueAsString(order);
            jmsTemplate.convertAndSend(topic, orderAsJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return order;
    }
}
