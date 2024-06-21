package com.spring.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer>{
	
}
