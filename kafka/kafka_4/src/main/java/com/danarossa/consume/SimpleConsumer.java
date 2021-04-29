package com.danarossa.consume;

import com.danarossa.Topics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileReader;
import java.util.*;

public class SimpleConsumer {
    private static final double c = Math.pow(10, -6);
    private static final double d = 0.5;

    // docker exec -it kafka-docker_kafka_1 bash
    // kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic users_auth
    // kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic users_auth

    public static void main(String[] args) throws Exception {
        HashMap<String, Double> sums = new HashMap<>();

        Properties props = new Properties();
        props.load(SimpleConsumer.class.getClassLoader().getResourceAsStream("kafka.properties"));

//        props.load(new FileReader("kafka.properties"));

        KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(props);

        try {
            // List of topics to subscribe to
            consumer.subscribe(Topics.getTopicsNames());
            System.out.println("here ");
            while (true) {
                System.out.println("records");
                ConsumerRecords<String, Integer> records = consumer.poll(10);
                System.out.println(records);
                for (ConsumerRecord<String, Integer> record : records) {
                    String topicName = record.topic();
                    Integer value = record.value();
                    System.out.println("value " + value + " from " + topicName);
                    double newSum;
                    if (sums.get(topicName) == null) {
                        newSum = value * (1 - c);
                    } else {
                        newSum = sums.get(topicName) * (1 - c) + value;
                    }
                    sums.remove(topicName);
                    if (newSum > d) {
                        sums.put(topicName, newSum);
                        Map.Entry<String, Double> maxEntry = Collections.max(sums.entrySet(), Map.Entry.comparingByValue());
                        System.out.format("Maximum value is from topic %s is %f", maxEntry.getKey(), maxEntry.getValue());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }


}

