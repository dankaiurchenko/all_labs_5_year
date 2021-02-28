package com.danarossa;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SimpleProducer {


    // docker exec -it kafka-docker_kafka_1 bash
    // kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic users_auth
    // kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic users_auth

    public static void main(String[] args) throws Exception {
        // name of created topic
//        String topicName = "test";
        String topicName = "users_auth";

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
                "com.danarossa.JsonSerializerDeserializer");
        Producer<String, Entry> producer = new KafkaProducer<>(props);

        while(true){
            Entry entry = new Entry();
            producer.send(new ProducerRecord<>(topicName, entry.getUserId().toString(), entry ));
            TimeUnit.SECONDS.sleep(2);
            producer.flush();

            System.out.println("sent : " + entry.toString());
        }
    }


}