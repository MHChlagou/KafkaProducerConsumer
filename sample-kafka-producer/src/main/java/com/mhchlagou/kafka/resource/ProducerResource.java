package com.mhchlagou.kafka.resource;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhchlagou.kafka.model.Producer;

@RestController
@RequestMapping("kafka")
public class ProducerResource {
	
	private String TOPIC = "AutoCreateTopics";
	
	@Autowired
	private KafkaTemplate<String, Producer> kafkaTemplate;
	
	@Bean
	public NewTopic defaultTopic() {
		return new NewTopic(TOPIC, 1, (short)1);
	}
	
	@GetMapping(value = "/publish/{name}")
	public String send(@PathVariable("name") String name) {
		
		kafkaTemplate.send(TOPIC, new Producer(name, "Informatique", 60000L));
		return "Message was published successfully to the topic : " + TOPIC;
	}

}