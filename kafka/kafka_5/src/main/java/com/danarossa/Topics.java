package com.danarossa;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Topics {
    private static final String topic1Name = "topic1";
    private static final String topic2Name = "topic2";

    public static String getTopic1Name() {
        return topic1Name;
    }

    public static String getTopic2Name() {
        return topic2Name;
    }

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(Topics.class.getClassLoader().getResourceAsStream("kafka.properties"));

            AdminClient adminClient = AdminClient.create(properties);
            List<NewTopic> newTopics = Arrays.asList(
                    new NewTopic(topic1Name, 1, (short) 1),
                    new NewTopic(topic2Name, 1, (short) 1)
            );

            adminClient.createTopics(newTopics);
            adminClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}