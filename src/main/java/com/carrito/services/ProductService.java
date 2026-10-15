package com.carrito.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carrito.models.Product;
import com.carrito.repositories.ProductRepo;

@Service
public class ProductService {
	
	private final ProductRepo productRepo;
	
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}
	
	public Optional<Product> getProductById(Long id) {
		
		return productRepo.findById(id);
	}
	
	public void saveProduct(Product product) {
		
		productRepo.save(product);
	}
	
	public void deleteProduct(Product product) {
		
		productRepo.delete(product);
	}

}
