package com.kafkademo.plain.kafkademo.plain.kafka;

import com.kafkademo.plain.kafkademo.plain.config.KafkaStreamsConfig;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.*;
import static org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_BUILDER_BEAN_NAME;

@Component
@AllArgsConstructor
public class DemoTopology {

    private static final Serde<String> STRING_SERDE = Serdes.String();


    @Bean(name = DEFAULT_STREAMS_BUILDER_BEAN_NAME )
    public FactoryBean<StreamsBuilder> streamsBuilderFactoryBean2() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-2");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(props);
        StreamsBuilderFactoryBean streamsBuilder = new StreamsBuilderFactoryBean(streamsConfig);
        streamsBuilder.setSingleton(Boolean.TRUE);



        return streamsBuilder;
    }

    @Bean
    public KStream<String, String> demoKStream(@Autowired StreamsBuilder streamsBuilderNew) {
        KStream<String, String> kstream = streamsBuilderNew.stream("demo-topic-1");
        kstream.to("demo-topic-2");
        kstream.foreach((key, value) -> {});
        return kstream;

    }










}
