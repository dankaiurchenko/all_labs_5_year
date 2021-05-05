package com.danarossa.produce;

import com.danarossa.Entry;
import com.danarossa.Topics;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    // docker exec -it kafka-docker_kafka_1 bash
    // kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic users_auth
    // kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic users_auth

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(SimpleProducer.class.getClassLoader().getResourceAsStream("kafka.properties"));

        Producer<String, String> producer = new KafkaProducer<>(props);

        while (true) {
            int random = (int) (Math.random() * 1000);
            producer.send(new ProducerRecord<>(Topics.getTopicsName(), Entry.getRandomBigInteger().toString(), String.valueOf(random)));
            producer.flush();
        }
    }


}