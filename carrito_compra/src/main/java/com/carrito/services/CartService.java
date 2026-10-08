package com.carrito.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.carrito.models.Product;

@Service
@SessionScope
public class CartService {
	
	private final List<Product> cartProducts = new ArrayList<>();
	
	public void addProduct(Product product) {
		
		cartProducts.add(product);
	}
	
	public void deleteProduct(Long id) {
		
		cartProducts.removeIf(product -> product.getId().equals(id));
	}
	
	public List<Product> getProducts() {
		
		return cartProducts;
	}
	
}
