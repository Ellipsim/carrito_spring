package com.carrito.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carrito.services.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final ProductService productService; 

	public AdminController(ProductService productService) {
		
		this.productService = productService;
	}
	
    @GetMapping
    public String admin(Model model) {
    	
    	model.addAttribute("productList", productService.getAllProducts());
    	
        return "admin";
    }
}