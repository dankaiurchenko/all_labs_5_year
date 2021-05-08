package com.danarossa;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Topics {
    private static final List<String> topicsNames = IntStream.range(0, 100).mapToObj(i -> i + "_topic").collect(Collectors.toList());

    public static List<String> getTopicsNames() {
        return topicsNames;
    }

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(Topics.class.getClassLoader().getResourceAsStream("kafka.properties"));

            AdminClient adminClient = AdminClient.create(properties);
            List<NewTopic> newTopics = topicsNames
                    .stream()
                    .map(topicName -> new NewTopic(topicName, 1, (short) 1))
                    .collect(Collectors.toList());

            adminClient.createTopics(newTopics);
            adminClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}