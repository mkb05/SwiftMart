package com.service.payment.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.payment.Entity.Payment;
import com.service.payment.Repository.PaymentRepository;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@GetMapping
	public List<Payment> getAllPayments(){
		return paymentRepository.findAll();
	}
	
	@GetMapping("/{orderId}")
	public Payment getPaymentByOrderId(@PathVariable Long orderId) {
		return paymentRepository.findAll().stream()
				.filter(p -> p.getOrderId().equals(orderId))
				.findFirst()
				.orElse(null);
	}
}
