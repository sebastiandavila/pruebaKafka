package com.example.pruebakafka;

import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class KafkaMessageProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = Logger.getLogger(KafkaMessageProducer.class.getName());

    public void sendMessage(String topic, String message) {

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info(String.format("Sent message=[%s] with offset=[%s]", message, result.getRecordMetadata().offset()));
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.log(Level.SEVERE,  String.format("Unable to send message=[%s] due to : %s",message, ex.getMessage()));
            }
        });
    }

    private boolean validationTopic(String topic) {
        return topic == null || topic.trim().equals("");
    }
}
