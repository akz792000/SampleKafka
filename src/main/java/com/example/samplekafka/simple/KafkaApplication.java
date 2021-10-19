package com.example.samplekafka.simple;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageConsumer consumer = context.getBean(MessageConsumer.class);

        producer.sendMessage("Hello, World!");
        consumer.latch.await(10, TimeUnit.SECONDS);

        context.close();
    }

}
