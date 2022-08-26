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


@Component
public class Subscriber {
    Logger logger = LoggerFactory.getLogger(Publisher.class);

    private CountDownLatch countDownLatch = new CountDownLatch(3);
    @JmsListener(destination = "${activemq.topic}")
    public void consumeMessage1(String message) {

        logger.info("Message1 received from activemq queue---"+message);
        countDownLatch.countDown();
    }
    @JmsListener(destination = "${activemq.topic}")
    public void consumeMessage2(String message) {

        logger.info("Message2 received from activemq queue---"+message);
        countDownLatch.countDown();
    }
    @JmsListener(destination = "${activemq.topic}")
    public void consumeMessage3(String message) {

        logger.info("Message3 received from activemq queue---"+message);
        countDownLatch.countDown();
    }

}
