package com.kafkademo.plain.kafkademo.plain.kafka;

import com.kafkademo.plain.kafkademo.plain.config.KafkaStreamsConfig;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.*;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
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


    @Bean
    @Qualifier("primaryStreamsBuilder")
    @Primary
    public StreamsBuilderFactoryBean demoKStreamsBuilder(@Qualifier("primaryStreamsConfig") KafkaStreamsConfiguration streamsConfig) {
        StreamsBuilderFactoryBean streamsBuilderFactoryBean = new StreamsBuilderFactoryBean(streamsConfig);
        streamsBuilderFactoryBean.setSingleton(Boolean.TRUE);

        streamsBuilderFactoryBean.setStateListener(((newState, oldState) ->  {
            if(newState.equals(KafkaStreams.State.RUNNING)) {
                System.out.println(1);

                KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
                ReadOnlyKeyValueStore<String, Long> demoStore = kafkaStreams.store(
                        StoreQueryParameters.fromNameAndType("demo-store-1", QueryableStoreTypes.keyValueStore())
                );

                demoStore.get("demo-key-1");

            }
        }));
        return streamsBuilderFactoryBean;
    }

    @Bean(name = "primaryStreamsConfig")
    public KafkaStreamsConfiguration streamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-app-2");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        KafkaStreamsConfiguration streamsConfig = new KafkaStreamsConfiguration(props);
        return streamsConfig;
    }



    @Bean
    public KStream<String, String> demoKStream(@Qualifier("primaryStreamsBuilder") StreamsBuilder demoKStreamsBuilder) {
        KStream<String, String> kstream = demoKStreamsBuilder.stream("demo-topic-1");
        kstream.to("demo-topic-2");
        kstream.foreach((key, value) -> {});
        return kstream;

    }

    @Bean
    public KTable<String, String> demoKTable(@Qualifier("primaryStreamsBuilder") StreamsBuilder demoKStreamsBuilder) {

        StoreBuilder demoStateStore = Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore("demo-store-1"),STRING_SERDE, STRING_SERDE)
                .withLoggingEnabled(new HashMap<>());
        demoKStreamsBuilder.stream("demo-topic-1", Consumed.with(Serdes.String(), STRING_SERDE)).to("demo-output-topic",Produced.with(Serdes.String(), STRING_SERDE));
        KTable<String, String> demoTable = demoKStreamsBuilder.table("demo-output-topic", Consumed.with(Serdes.String(), STRING_SERDE),Materialized.as(demoStateStore.name()));

        return demoTable;
    }

    @Bean
    public KafkaStreams.StateListener kafkaStreamsStateListener(StreamsBuilderFactoryBean streamsBuilderFactoryBean) {
        return (newState, oldState) -> {


            if(newState == KafkaStreams.State.RUNNING) {
                KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
                ReadOnlyKeyValueStore<String, Long> demoStore = kafkaStreams.store(
                        StoreQueryParameters.fromNameAndType("demo-store-1", QueryableStoreTypes.keyValueStore())
                );

                demoStore.get("demo-1");
            }
        };
    }









}
