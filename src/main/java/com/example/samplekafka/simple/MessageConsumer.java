package com.example.samplekafka.simple;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @KafkaListeners({
            @KafkaListener(topics = "${simple.topic.name}", containerFactory = "firstListenerContainerFactory"),
            @KafkaListener(topics = "${simple.topic.name}", containerFactory = "secondListenerContainerFactory")
    })
    public void listener(String message,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.GROUP_ID) String groupId,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message in Topic '" + topic + "' GroupId: " + groupId + " Partition: " + partition + " Message: " + message);
    }

    @KafkaListeners({
            @KafkaListener(topics = "${simple.topic.name}", containerFactory = "firstAgainListenerContainerFactory"),
    })
    public void listenerAgain(String message,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                              @Header(KafkaHeaders.GROUP_ID) String groupId,
                              @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Again Message in Topic '" + topic + "' GroupId: " + groupId + " Partition: " + partition + " Message: " + message);
    }


}
