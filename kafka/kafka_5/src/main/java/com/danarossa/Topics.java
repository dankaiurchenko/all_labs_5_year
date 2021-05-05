package com.danarossa;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Topics {
    private static final String topicsName = "re[eating_numbers";

    public static String getTopicsName() {
        return topicsName;
    }

    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            properties.load(Topics.class.getClassLoader().getResourceAsStream("kafka.properties"));

            AdminClient adminClient = AdminClient.create(properties);
            NewTopic newTopics = new NewTopic(topicsName, 1, (short) 1);

            adminClient.createTopics(Collections.singletonList(newTopics));
            adminClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}