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
		
	    for (int i = 0; i < cartProducts.size(); i++) {
	        if (cartProducts.get(i).getId().equals(id)) {
	            cartProducts.remove(i);
	            break;
	        }
	    }
	}
	
	public void deleteAllByProductId(Long id) {
	    cartProducts.removeIf(product -> product.getId().equals(id));
	}
	
	public List<Product> getProducts() {
		
		return cartProducts;
	}
	
}
