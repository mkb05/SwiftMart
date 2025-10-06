package com.service.payment.Service;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.dto.OrderEvent;
import com.example.common.dto.PaymentEvent;
import com.service.payment.Entity.Payment;
import com.service.payment.Repository.PaymentRepository;



@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentEvent processPayment(OrderEvent orderEvent) {
		boolean success=new Random().nextBoolean();
		
		
		Payment payment=new Payment(
				orderEvent.getId(),
				BigDecimal.valueOf(orderEvent.getTotalPrice()),
				success? "SUCCESS":"FAILED",
				Uuid.randomUuid().toString()
				);
		
		paymentRepository.save(payment);
		
		return new PaymentEvent(
				orderEvent.getId(),
				payment.getStatus(),
				payment.getTransactionId()
				);
		
	}
}
