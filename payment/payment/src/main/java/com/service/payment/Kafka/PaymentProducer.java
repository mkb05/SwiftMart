package com.service.payment.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.common.dto.PaymentEvent;


@Service
public class PaymentProducer {

	@Autowired
	private KafkaTemplate<String,PaymentEvent> kafkaTemplate;
	
	private static final String TOPIC="payment-events";
	
	public void sendPaymentEvent(PaymentEvent event) {
		
		System.out.print("Processed payment" +event);
		
		kafkaTemplate.send(TOPIC,event);
		System.out.print("Sent Payment Event: " +event);
	}
}
