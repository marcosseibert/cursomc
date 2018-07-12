package com.seibert.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.repositories.CategoryRepository;
import com.seibert.cursomc.services.exception.ObjectNotFoundException;


@Service	
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> category = repo.findById(id);
		
		return category.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id:" + id + " Type:" + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Category update(Category obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.seibert.cursomc.services.exception.DataIntegrityViolationException("Cannot exclude category because it has associated products!");
		}
	}

	public List<Category> findAll() {
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		return repo.findAll(pageRequest);
	}
}
