package com.mhchlagou.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.mhchlagou.kafka.model.Producer;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

		@Bean
		public ConsumerFactory<String, Producer> consumerFactory(){
			Map<String, Object> config = new HashMap<>();	
			config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
			config.put(ConsumerConfig.GROUP_ID_CONFIG, "DevOps");
			config.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, false);
			config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
			config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
			config.put(ConsumerConfig.CLIENT_ID_CONFIG, "HEDI");
			config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
			return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), 
					new JsonDeserializer<>(Producer.class));
		}
		
		@Bean
		public ConcurrentKafkaListenerContainerFactory<String, Producer> kafkaListenerContainerFactory(){
			ConcurrentKafkaListenerContainerFactory<String, Producer> factory = new ConcurrentKafkaListenerContainerFactory<>();
			factory.setConsumerFactory(consumerFactory());
			return factory;
		}
}