package com.javedrpi.kafkaconsumer.config;

import com.javedrpi.kafkaconsumer.model.JsonLog;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaListenerConfig {

    @Bean
    public ConsumerFactory<String, JsonLog> jsonLogConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.100:32770");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "logger_app");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(JsonLog.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JsonLog> jsonLogKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, JsonLog> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(jsonLogConsumerFactory());
        return factory;
    }

}

