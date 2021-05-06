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
    //    private static final double c = Math.pow(10, -6);
    private static final double c = 0.01;
    private static final double d = 0.5;

    public static void main(String[] args) throws Exception {
        HashMap<String, Double> sums = new HashMap<>();

        Properties props = new Properties();
        props.load(SimpleConsumer.class.getClassLoader().getResourceAsStream("kafka.properties"));

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        try {
            // List of topics to subscribe to
            consumer.subscribe(Topics.getTopicsNames());
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
                    String topicName = record.topic();
                    int newValue = Integer.parseInt(record.value());

                    Double oldResult = sums.getOrDefault(topicName, 0.0);
                    double newSum = oldResult * c + newValue;
                    if (newSum > 0.5) {
                        sums.put(topicName, newSum);
                    } else {
                        sums.remove(topicName);
                    }
                    if (sums.size() > 1) {
                        Map.Entry<String, Double> maxEntry = Collections.max(sums.entrySet(), Map.Entry.comparingByValue());
                        System.out.format("Maximum value is from topic %s is %f", maxEntry.getKey(), maxEntry.getValue());
                        System.out.println();
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

