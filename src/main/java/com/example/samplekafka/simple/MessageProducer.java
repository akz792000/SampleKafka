package com.example.samplekafka.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${simple.topic.name}")
    private String simpleName;

    public void sendMessage(String message) {
        for (int i = 0; i < KafkaTopicConfig.SIMPLE_NUM_PARTITION; i++) {
            int partition = i;
            kafkaTemplate
                    // random partition as default
                    .send(simpleName, message)
                    //.send(simpleName, partition, null, message)
                    .addCallback(new ListenableFutureCallback<>() {

                        @Override
                        public void onSuccess(SendResult<String, String> result) {
                            System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "] (" + partition + ")");
                        }

                        @Override
                        public void onFailure(Throwable ex) {
                            System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage() + " (" + partition + ")");
                        }
                    });
        }
    }

}
