package com.danarossa.consume;

import com.danarossa.Topics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.streaminer.stream.cardinality.FlajoletMartin;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class SimpleConsumer {

    public static void main(String[] args) throws Exception {
        FlajoletMartin flajoletMartin = new FlajoletMartin(32, 1024, 32);

        Properties props = new Properties();
        props.load(SimpleConsumer.class.getClassLoader().getResourceAsStream("kafka.properties"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        Set<Integer> realUniqueElements = new HashSet<>();

        try {
            // List of topics to subscribe to
            consumer.subscribe(Collections.singletonList(Topics.getTopicsName()));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    int newValue = Integer.parseInt(record.value());
                    flajoletMartin.offer(newValue);
                    realUniqueElements.add(newValue);
                    System.out.println("estimated : " + flajoletMartin.cardinality() + " real " + realUniqueElements.size());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }


}

