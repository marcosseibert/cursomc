package com.seibert.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.domain.Product;
import com.seibert.cursomc.repositories.CategoryRepository;
import com.seibert.cursomc.repositories.ProductRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category ca1 = new Category(null, "Informática");
		Category ca2 = new Category(null, "Escritório");
		
		Product p1 = new Product(null, "Computer", 2500.00);
		Product p2 = new Product(null, "Printer", 500.00);
		Product p3 = new Product(null, "Mouse", 75.00);
		
		ca1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		ca2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(ca1));
		p2.getCategories().addAll(Arrays.asList(ca1, ca2));
		p3.getCategories().addAll(Arrays.asList(ca1));
		
		
		categoryRepository.saveAll(Arrays.asList(ca1, ca2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
	}
}
