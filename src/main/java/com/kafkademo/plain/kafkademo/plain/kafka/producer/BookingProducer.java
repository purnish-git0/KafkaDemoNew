package com.kafkademo.plain.kafkademo.plain.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_BUILDER_BEAN_NAME;

@Component
@AllArgsConstructor
public class BookingProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @Qualifier("primaryStreamsConfig")
    public KafkaStreamsConfiguration kafkaStreamsConfiguration;

    private final KafkaTemplate<?, ?> genericKafkaTemplate;


    public void pushToStreamingTopic(String topic) {

        String topicName = "demo-topic-1";
        CompletableFuture<SendResult<String, String>> streamingFuture = kafkaTemplate.send(topicName, "demo-key-1", "pushing-to-streaming-topic");
        streamingFuture.whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println(result.toString());
                    } else {
                        System.out.println("error");
                    }
                }
        );

    }

    public void getFromTable2() {

    }

    public void getFromTable() {

        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, Long> demoStore = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("demo-store-1", QueryableStoreTypes.keyValueStore())
        );

        demoStore.get("demo-1");
    }

    public void publishToTopic(String message) {
        String topicName = "custom-user-event";
        CompletableFuture<SendResult<String, String>> newFuture = kafkaTemplate.send(topicName, message);
        newFuture.whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println(result.toString());
                    } else {
                        System.out.println("error");
                    }
                }
            );
    }


}
