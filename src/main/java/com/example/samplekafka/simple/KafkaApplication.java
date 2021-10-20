package com.example.samplekafka.simple;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer producer = context.getBean(MessageProducer.class);

        producer.sendMessage("Hello, World!");
        Thread.sleep(20000);

        context.close();
    }

}
