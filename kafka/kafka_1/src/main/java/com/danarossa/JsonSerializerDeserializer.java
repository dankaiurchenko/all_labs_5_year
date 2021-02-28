package com.danarossa;


import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializerDeserializer implements Serializer<Entry>, Deserializer<Entry> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, Entry data) {

        try {
            return mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            return new byte[0];
        }
    }

    @Override
    public Entry deserialize(String topic, byte[] data) {

        try {
            return mapper.readValue(data, Entry.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }
}