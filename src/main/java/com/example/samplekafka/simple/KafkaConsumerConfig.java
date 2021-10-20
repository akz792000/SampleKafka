package com.example.samplekafka.simple;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory(String groupId, String clientId) {
        // default kafka consumer factory
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "20971520");
        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "20971520");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        DefaultKafkaConsumerFactory defaultKafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(props);

        // concurrent kafka listener container factory
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultKafkaConsumerFactory);
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> firstListenerContainerFactory() {
        return concurrentKafkaListenerContainerFactory("first", "FIRST");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> firstAgainListenerContainerFactory() {
        return concurrentKafkaListenerContainerFactory("first", "FIRST_AGAIN");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> secondListenerContainerFactory() {
        return concurrentKafkaListenerContainerFactory("second", "SECOND");
    }

}
