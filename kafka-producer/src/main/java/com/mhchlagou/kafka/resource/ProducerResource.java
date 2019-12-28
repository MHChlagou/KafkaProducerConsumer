package com.mhchlagou.kafka.resource;

import java.util.List;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhchlagou.kafka.model.Producer;
import com.mhchlagou.kafka.repository.UserJpaRepository;

@RestController
@RequestMapping("kafka")
public class ProducerResource {
	
	private static final Logger logger = LoggerFactory.getLogger(ProducerResource.class);
	
	private String TOPIC = "AutoCreateTopics";
	
	@Autowired
	private KafkaTemplate<String, Producer> kafkaTemplate;
	
	//-------------------------------- CRUD -----------------------------------------
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@PostMapping(value = "/load")
	public Producer loadProducer(@RequestBody Producer producer) {
		userJpaRepository.save(producer);
		return userJpaRepository.findByName(producer.getName());
	}
	
	//------------------------------------------------------------------------------------
	
	
	@Bean
	public NewTopic defaultTopic() {
		return new NewTopic(TOPIC, 1, (short)1);
	}
	
	@GetMapping(value = "/publish/all")
	public String send() {
		
		List<Producer> producers = userJpaRepository.findAll();
		if(producers != null) {
			for(Producer producer : producers) {
				kafkaTemplate.send(TOPIC, producer);
				logger.info(String.format("************** Producer Data ************** ->", producer));
			}
		}
		return "Message was published successfully to the topic : " + TOPIC;
	}

}