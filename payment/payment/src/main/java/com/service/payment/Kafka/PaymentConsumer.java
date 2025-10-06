package com.service.payment.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.common.dto.OrderEvent;
import com.example.common.dto.PaymentEvent;
import com.service.payment.Service.PaymentService;



@Service
public class PaymentConsumer {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PaymentProducer paymentProducer;
	
	@KafkaListener(topics="order-events-payment",groupId="payment-service")
	public void consume(OrderEvent orderEvent) {
		System.out.print("Received order event in payment service: " + orderEvent);
		PaymentEvent paymentEvent=paymentService.processPayment(orderEvent);
		
		paymentProducer.sendPaymentEvent(paymentEvent);
	}
}
