package com.seibert.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.domain.Product;
import com.seibert.cursomc.repositories.CategoryRepository;
import com.seibert.cursomc.repositories.ProductRepository;
import com.seibert.cursomc.services.exception.ObjectNotFoundException;

@Service	
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	public Product find(Integer id) {
		Optional<Product> obj = productRepo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id:" + id + " Type:" + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		List<Category> categories = categoryRepo.findAllById(ids);
		
		return productRepo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
