package com.seibert.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Order;
import com.seibert.cursomc.repositories.OrderRepository;
import com.seibert.cursomc.services.exception.ObjectNotFoundException;

@Service	
public class OrderService {
	
	@Autowired
	private OrderRepository repo;

	public Order getOrder(Integer id) {
		Optional<Order> category = repo.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id:" + id + " Type:" + Order.class.getName()));
	}
}
