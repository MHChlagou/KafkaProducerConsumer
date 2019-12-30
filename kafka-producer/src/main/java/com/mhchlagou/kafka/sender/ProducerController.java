package com.mhchlagou.kafka.sender;

import java.util.List;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
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
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.kafka.support.SendResult;
import com.mhchlagou.kafka.model.Producer;
import com.mhchlagou.kafka.repository.UserJpaRepository;

@RestController
@RequestMapping("kafka")
public class ProducerController {

	private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);
	private String TOPIC = "DynamicTopics";

	@Autowired
	private KafkaTemplate<String, Producer> kafkaTemplate;

	// -------------------------------- ADD Data to H2 DB -----------------------------------------

	@Autowired
	private UserJpaRepository userJpaRepository;

	@PostMapping(value = "/load")
	public Producer loadProducer(@RequestBody Producer producer) {
		userJpaRepository.save(producer);
		return userJpaRepository.findByName(producer.getName());
	}

	// --------------------------------------------------------------------------------------------

	@Bean
	public NewTopic defaultTopic() {
		return new NewTopic(TOPIC, 2, (short) 1);
	}

	@GetMapping(value = "/publish/all")
	public String send() {
		List<Producer> producers = userJpaRepository.findAll();
		if (producers != null) {
			for (Producer producer : producers) {
				ProducerRecord<String, Producer> record = new ProducerRecord<String, Producer>(TOPIC, producer);
				record.headers().add("Authentification", "superUser".getBytes());
				ListenableFuture<SendResult<String, Producer>> future = this.kafkaTemplate.send(record);
				future.addCallback(new SuccessCallback<SendResult<String, Producer>>() {

					@Override
					public void onSuccess(SendResult<String, Producer> result) {
						System.out.println(result);
					}
				}, new FailureCallback() {

					@Override
					public void onFailure(Throwable ex) {
						ex.printStackTrace();
					}
				});
				logger.info(String.format("************** Producer Data ************** ->", producer));
				this.kafkaTemplate.flush();
			}
		}
		return "Message was published successfully to the topic : " + TOPIC;
	}

}