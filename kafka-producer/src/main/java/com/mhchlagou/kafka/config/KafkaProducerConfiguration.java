package com.mhchlagou.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mhchlagou.kafka.model.Producer;



@Configuration
public class KafkaProducerConfiguration {

		@Bean
		public ProducerFactory<String, Producer> producerFactory() {
			Map<String, Object> config = new HashMap<>();
			config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
			config.put(ProducerConfig.CLIENT_ID_CONFIG, "HEDI");
			config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
			config.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, "io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor");
			return new DefaultKafkaProducerFactory<>(config);
		}
		
		@Bean
		public KafkaTemplate<String, Producer> kafkaTemplate(){
			return new KafkaTemplate<>(producerFactory());
		}
}
