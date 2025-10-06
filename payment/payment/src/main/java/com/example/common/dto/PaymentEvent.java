package com.example.common.dto;


public class PaymentEvent {

	private Long orderId;
	private String paymentStatus;
	private String transactionId;
	public PaymentEvent(Long orderId, String paymentStatus, String transactionId) {
		super();
		this.orderId = orderId;
		this.paymentStatus = paymentStatus;
		this.transactionId = transactionId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	
	
}
