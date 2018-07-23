package com.seibert.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.PaymentWithTicket;

@Service
public class TicketService {

	public void fillPaymentWithTicket(PaymentWithTicket payment, Date orderDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		payment.setPaymentDate(calendar.getTime());
	}
}
