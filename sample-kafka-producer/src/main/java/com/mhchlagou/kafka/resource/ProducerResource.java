package com.mhchlagou.kafka.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhchlagou.kafka.model.Producer;

@RestController
@RequestMapping("kafka")
public class ProducerResource {
	
	@Autowired
	private KafkaTemplate<String, Producer> kafkaTemplate;
	
	private static final String TOPIC = "Messaging_Transaction";
	
	@GetMapping(value = "/publish/{name}")
	public String send(@PathVariable("name") String name) {
		
		kafkaTemplate.send(TOPIC, new Producer(name, "Informatique", 60000L));
		return "Message was published successfully";
	}

}
