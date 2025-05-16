package com.kafkademo.plain.kafkademo.plain.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {
    private static final int PREMIUM_PARTITION = 0;
    private static final int NORMAL_PARTITION = 1;

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        if(((String) key).equals("1")) {
            return 1;
        }
        if(((String) key).equals("2")) {
            return 2;
        }
        if(((String) key).equals("3")) {
            return 3;
        }
        return 4;
    }

    @Override
    public void close() {

    }

    private String extractCustomerType(String key) {
        String[] parts = key.split("_");
        return parts.length > 1 ? parts[1] : "normal";
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }

    // more methods
}