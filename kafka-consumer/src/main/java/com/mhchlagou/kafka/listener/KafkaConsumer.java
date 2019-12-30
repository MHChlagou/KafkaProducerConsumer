package com.mhchlagou.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.nio.charset.StandardCharsets;
import com.mhchlagou.kafka.model.Producer;

@Service
public class KafkaConsumer {
		
		@KafkaListener(topics = "DynamicTopics", groupId = "DevOps")
		public void listener(ConsumerRecord<String, Producer> record, Producer producer) {
			record.headers().forEach( header -> {
				String value = new String(header.value(), StandardCharsets.UTF_8); 
				System.out.println("Key :"+ header.key() +", Value :"+value);
			});
			System.out.println(record);
			System.out.println(record.value());
			System.out.println("Consumed message: " + producer);
		}
}