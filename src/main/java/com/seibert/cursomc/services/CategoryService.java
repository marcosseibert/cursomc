package com.seibert.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.repositories.CategoryRepository;

@Service	
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;

	public Category getCategory(Integer id) {
		Optional<Category> category = repo.findById(id);
		return category.orElse(null);
	}
}
