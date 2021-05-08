package com.danarossa.produce;

import com.danarossa.Entry;
import com.danarossa.Topics;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(SimpleProducer.class.getClassLoader().getResourceAsStream("kafka.properties"));

        Producer<String, String> producer = new KafkaProducer<>(props);

        while (true) {
            int random = (int) (Math.random() * 100);
            producer.send(new ProducerRecord<>(Topics.getTopicsName(), Entry.getRandomBigInteger().toString(), String.valueOf(random)));
            producer.flush();
            System.out.println("flushed " + random);
        }
    }

}