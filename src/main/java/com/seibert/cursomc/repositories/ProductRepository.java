package com.seibert.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seibert.cursomc.domain.Category;
import com.seibert.cursomc.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
