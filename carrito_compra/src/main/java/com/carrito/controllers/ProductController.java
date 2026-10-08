package com.carrito.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carrito.models.Product;
import com.carrito.services.ProductService;

@Controller
@RequestMapping("products")
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		
		this.productService = productService;
	}
	
	@GetMapping
	public String listProducts(Model model) {
		
		List<Product> products = productService.getAllProducts();
		
		model.addAttribute("productList", products);
		
		return "productos";
	}
	
	@GetMapping("/add")
	public String addProduct(Model model) {
	
		model.addAttribute("product", new Product());
		model.addAttribute("editMode", false);
		
		return "formulario-productos";
	}
	
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		
		Optional<Product> product = productService.getProductById(id);
		
		if (product.isPresent())  {
			model.addAttribute("product", product.get());
			model.addAttribute("editMode", true);
		}
		// NOTE: Se podría manejar el caso null informando de que el producto no 
		// existe y dando el formulario para crear objeto nuevo por defecto
		
		return "formulario-productos";
	}	
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) {
		
		productService.saveProduct(product);
		
		return "redirect:/admin";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		
		Optional<Product> product = productService.getProductById(id);
		
		if (product.isPresent()) productService.deleteProduct(product.get());
		// NOTE: Se podría imprimir un mensaje de fallo al intentar encontrar el dato
		
		return "redirect:/admin";
	}
}
