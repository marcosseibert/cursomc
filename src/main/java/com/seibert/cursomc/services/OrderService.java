package com.seibert.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Order;
import com.seibert.cursomc.domain.OrderItem;
import com.seibert.cursomc.domain.PaymentWithTicket;
import com.seibert.cursomc.domain.enums.PaymentStatus;
import com.seibert.cursomc.repositories.OrderItemRepository;
import com.seibert.cursomc.repositories.OrderRepository;
import com.seibert.cursomc.repositories.PaymentRepository;
import com.seibert.cursomc.repositories.ProductRepository;
import com.seibert.cursomc.services.exception.ObjectNotFoundException;

@Service	
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public Order find(Integer id) {
		Optional<Order> category = repo.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id:" + id + " Type:" + Order.class.getName()));
	}

	public Order insert(Order obj) {
		
		obj.setId(null);
		obj.setOrderDate(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket payment = (PaymentWithTicket) obj.getPayment();
			ticketService.fillPaymentWithTicket(payment, obj.getOrderDate());
		}
		obj = repo.save(obj);
		
		paymentRepository.save(obj.getPayment());
		
		for (OrderItem item : obj.getItems()) {
			item.setDiscount(0.0);
			item.setPrice(productService.find(item.getProduct().getId()).getPrice());
			item.setOrder(obj);
		}
		
		orderItemRepository.saveAll(obj.getItems());
		
		return obj;
	}
}
