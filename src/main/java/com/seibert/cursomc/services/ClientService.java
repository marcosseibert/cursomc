package com.seibert.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Client;
import com.seibert.cursomc.dto.ClientDTO;
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
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.seibert.cursomc.services.exception.DataIntegrityViolationException("Cannot exclude category because it has associated products!");
		}
	}

	public List<Client> findAll() {
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(),objDTO.getEmail(),null,null);
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
