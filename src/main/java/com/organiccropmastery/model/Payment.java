package com.organiccropmastery.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payment {
	@Id
	private String orderId;
	private String currency;
	private String attempts;
	private String amount;
	private String status;
	private String orderEntity;

	@Override
	public String toString() {
		return "Payment [orderId=" + orderId + ", currency=" + currency + ", attempts=" + attempts + ", amount="
				+ amount + ", status=" + status + ", orderEntity=" + orderEntity + "]";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAttempts() {
		return attempts;
	}

	public void setAttempts(String attempts) {
		this.attempts = attempts;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(String orderEntity) {
		this.orderEntity = orderEntity;
	}

	public Payment(String orderId, String currency, String attempts, String amount, String status, String orderEntity) {
		super();
		this.orderId = orderId;
		this.currency = currency;
		this.attempts = attempts;
		this.amount = amount;
		this.status = status;
		this.orderEntity = orderEntity;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
