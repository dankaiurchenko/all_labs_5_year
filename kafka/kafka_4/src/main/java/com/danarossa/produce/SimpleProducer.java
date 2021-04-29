package com.danarossa.produce;

import com.danarossa.Entry;
import com.danarossa.Topics;
import com.danarossa.consume.SimpleConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileReader;
import java.util.Properties;

public class SimpleProducer {


    // docker exec -it kafka-docker_kafka_1 bash
    // kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic users_auth
    // kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic users_auth

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
//        props.load(new FileReader("kafka.properties"));
        props.load(SimpleProducer.class.getClassLoader().getResourceAsStream("kafka.properties"));

        Producer<String, Integer> producer = new KafkaProducer<>(props);

        while (true) {
            for (String topicName : Topics.getTopicsNames()) {
                int random = (int) Math.round(Math.random());
                producer.send(new ProducerRecord<>(topicName, Entry.getRandomBigInteger().toString(), random));
                producer.flush();
                System.out.println("flushed " + random + " to " + topicName);
            }

        }
    }


}