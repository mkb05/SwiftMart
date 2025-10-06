package com.service.payment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.payment.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

	
}
