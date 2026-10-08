package com.carrito.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carrito.models.Product;
import com.carrito.services.CartService;
import com.carrito.services.ProductService;

@Controller
@RequestMapping("cart")
public class CartController {
	
	private final CartService cartService;
	private final ProductService productService;
	
	public CartController(CartService cartService, ProductService productService) {
		
		this.cartService = cartService;
		this.productService = productService;
	}
	
	@GetMapping
	public String listCart(Model model) {
		
		model.addAttribute("cart", cartService.getProducts());
		return "cart";
	}
	
	@PostMapping("add/{id}")
	public String addProduct(@PathVariable Long id, Model model) {
		
		Optional<Product> product = productService.getProductById(id);
		
		if (product.isPresent()) cartService.addProduct(product.get());
		// NOTE: Se podría manejar el caso null informando de que el producto no 
		// existe y no añadir ningún producto al carrito
		
		model.addAttribute("cart", product);
		
		return "redirect:/cart";
	}
	
	@PostMapping("remove/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartService.deleteProduct(id);

        return "redirect:/cart";
    }
}
