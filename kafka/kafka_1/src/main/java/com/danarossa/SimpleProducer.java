package com.danarossa;

import java.util.*;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

    static Set<String> names = Set.of("Simon Pegg", "Nick Frost", "Edgar Wright");


    public static void main(String[] args) throws Exception {
        // name of created topic
        String topicName = "test";

        Properties props = new Properties();
        // exposed port
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);

        props.put("buffer.memory", 33554432);
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++){
            // sends numbers from 0 to 10 to the topic
            String randomName = getRandomName();
            producer.send(new ProducerRecord<>(topicName,
                    randomName, randomName + "_updated_new_photo" ));
        }
        System.out.println("Messages sent successfully");
        producer.close();
    }

    private static String getRandomName() {
        int index = new Random().nextInt(names.size());
        Iterator<String> iter = names.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }
}