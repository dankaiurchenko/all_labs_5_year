package com.danarossa.consume;

import com.danarossa.Topics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SimpleConsumer {

    public static void main(String[] args) throws Exception {
        int uniqueElements = 0;

        Properties props = new Properties();
        props.load(SimpleConsumer.class.getClassLoader().getResourceAsStream("kafka.properties"));
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        try {
            // List of topics to subscribe to
            consumer.subscribe(Collections.singletonList(Topics.getTopicsName()));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    int newValue = Integer.parseInt(record.value());


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }

    }


}

