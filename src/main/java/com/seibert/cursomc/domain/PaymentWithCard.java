package com.seibert.cursomc.domain;

import javax.persistence.Entity;

import com.seibert.cursomc.domain.enums.PaymentStatus;

@Entity
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public PaymentWithCard() {
		
	}

	public PaymentWithCard(Integer id, PaymentStatus status, Order order, Integer numberOfInstallments) {
		super(id, status, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}

}
