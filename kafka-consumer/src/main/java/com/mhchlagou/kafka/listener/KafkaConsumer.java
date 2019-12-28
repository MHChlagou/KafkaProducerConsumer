package com.mhchlagou.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.mhchlagou.kafka.model.Producer;

@Service
public class KafkaConsumer {
		
		@KafkaListener(topics = "AutoCreateTopics", groupId = "DevOps")
		public void producer(Producer producer) {
			System.out.println("Consumed message: " + producer);
		}
}