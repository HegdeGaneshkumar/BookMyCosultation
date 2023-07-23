package com.upgrad.notificationservice.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

public class KafkaConsumer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "ec2-52-73-240-46.compute-1.amazonaws.com:9092");
        properties.setProperty("group.id", "doctor-service");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("doctor-service"));

        Set<String> subsribedTopics = consumer.subscription();

        subsribedTopics.stream().forEach(System.out::println);

        try{
            while(true){
                ConsumerRecords<String, String > records = consumer.poll(Duration.ofMillis(100));
                for(ConsumerRecord<String, String> record: records){
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        }
        finally {
            consumer.close();
        }
    }
}
