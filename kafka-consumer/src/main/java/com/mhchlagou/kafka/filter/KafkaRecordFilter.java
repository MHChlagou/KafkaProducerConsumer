package com.mhchlagou.kafka.filter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

@Component
public class KafkaRecordFilter implements RecordFilterStrategy<Object, Object>{
	
	@Override
	public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
		Headers headers = consumerRecord.headers();
		if (headers != null && headers.headers("Authentification") != null && headers.headers("Authentification").iterator().hasNext()) {
				return !("superUser".equals(new String(consumerRecord.headers().headers("Authentification").iterator().next().value(),
						StandardCharsets.UTF_8)));
		}
		return true;
	}
}
