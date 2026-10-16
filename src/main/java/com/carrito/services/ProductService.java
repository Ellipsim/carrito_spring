package com.carrito.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrito.models.Product;
import com.carrito.repositories.CartItemRepo;
import com.carrito.repositories.ProductRepo;

@Service
public class ProductService {
	
	private final ProductRepo productRepo;
	private final CartItemRepo cartItemRepo;
	
	public ProductService(ProductRepo productRepo, CartItemRepo cartItemRepo) {
		this.productRepo = productRepo;
		this.cartItemRepo = cartItemRepo;
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
	
	@Transactional
	public void deleteProduct(Product product) {
	    cartItemRepo.deleteByProductId(product.getId());
		productRepo.delete(product);
	}

}
