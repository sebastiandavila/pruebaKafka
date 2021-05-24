package com.example.pruebakafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class KafkaTestListener {
    private static final Logger logger = Logger.getLogger(KafkaTestListener.class.getName());

    @KafkaListener(topics = "${message.topic.name}", groupId = "${message.group.name}")
    public void listenTopic1(String message) {
        logger.log(Level.INFO, "Recieved Message of topic1 in  listener: {0}", message);
    }

    @KafkaListener(topics = "${message.topic.name2}", groupId = "${message.group.name}")
    public void listenTopic2(String message) {
        logger.log(Level.INFO,"Recieved Message of topic2 in  listener: {0}", message);
    }
}
