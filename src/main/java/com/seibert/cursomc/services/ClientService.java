package com.seibert.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.domain.Client;
import com.seibert.cursomc.repositories.ClientRepository;
import com.seibert.cursomc.services.exception.ObjectNotFoundException;

@Service	
public class ClientService {
	
	@Autowired
	private ClientRepository repo;

	public Client find(Integer id) {
		Optional<Client> client = repo.findById(id);
		
		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id:" + id + " Type:" + Client.class.getName()));
	}
}
