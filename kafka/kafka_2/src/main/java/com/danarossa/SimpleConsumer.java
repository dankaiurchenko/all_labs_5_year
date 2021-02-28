package com.danarossa;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class SimpleConsumer {


    // docker exec -it kafka-docker_kafka_1 bash
    // kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic users_auth
    // kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic users_auth

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "suspicious_activity_group");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.danarossa.JsonSerializerDeserializer");

        KafkaConsumer<String, Entry> consumer = new KafkaConsumer<>(props);

        try {
            // List of topics to subscribe to
            consumer.subscribe(Collections.singletonList("users_auth"));
            while (true) {
                ConsumerRecords<String, Entry> records = consumer.poll(100);
                records.forEach((record) -> {
                    if (record.value().getUserId().hashCode() % 10 == 0) {
                        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }


}

