package com.carrito.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
		
		// Comprobar si algún producto ha sido borrado de la base de datos
		// Si ya no existe, se borra del carrito
		for (Product product : new ArrayList<>(cartService.getProducts())) {
	        if (productService.getProductById(product.getId()).isEmpty()) {
	            cartService.deleteAllByProductId(product.getId()); 
	        }
	    }	
		
		List<Product> cart = new ArrayList<>(cartService.getProducts());
		
		cart.sort(Comparator.comparing(Product::getName));
		
		
		
		model.addAttribute("cart", cart);
		return "cart";
	}
	
	@PostMapping("add/{id}")
	public String addProduct(@PathVariable Long id, Model model) {
		
		Optional<Product> product = productService.getProductById(id);
		
		if (product.isPresent()) cartService.addProduct(product.get());
		// NOTE: Se podría manejar el caso null informando de que el producto no 
		// existe y no añadir ningún producto al carrito
		
		model.addAttribute("cart", product);
		
		return "redirect:/products";
	}
	
	@PostMapping("remove/{id}")
    public String removeFromCart(@PathVariable Long id) {

        cartService.deleteProduct(id);

        return "redirect:/cart";
    }
}
