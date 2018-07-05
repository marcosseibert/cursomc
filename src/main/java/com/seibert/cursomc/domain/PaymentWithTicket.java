package com.seibert.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.seibert.cursomc.domain.enums.PaymentStatus;

@Entity
public class PaymentWithTicket extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Date expirationDate;
	private Date paymentDate;
	
	public PaymentWithTicket() {
		
	}

	public PaymentWithTicket(Integer id, PaymentStatus status, Order order, Date expirationDate, Date paymentDate) {
		super(id, status, order);
		this.expirationDate = expirationDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;	
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
