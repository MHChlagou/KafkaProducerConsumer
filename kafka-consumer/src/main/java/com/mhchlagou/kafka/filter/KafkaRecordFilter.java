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
		//if (headers != null && headers.headers("header1") != null && headers.headers("header1").iterator().hasNext()) {
			//if(consumerRecord.topic() != null) {	
				
				//return "test".equals(new String(consumerRecord.headers().headers("header1").iterator().next().value(),
					//	StandardCharsets.UTF_8));
				//return consumerRecord.topic().contains("_hedi");
		//}
		
		return false;
		
	}

}
