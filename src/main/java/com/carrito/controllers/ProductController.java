package com.carrito.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String listProducts(
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) Double minPrice,
	        @RequestParam(required = false) Double maxPrice,
	        Model model) {

	    List<Product> productList = productService.getAllProducts();

	    if (name != null && !name.isBlank()) {
	        productList = productList.stream()
	            .filter(p -> p.getName() != null &&
	                p.getName().toLowerCase()
	                    .contains(name.trim().toLowerCase()))
	            .toList();
	    }

	    if (minPrice != null) {
	        productList = productList.stream()
	            .filter(p -> p.getPrice() >= minPrice)
	            .toList();
	    }

	    if (maxPrice != null) {
	        productList = productList.stream()
	            .filter(p -> p.getPrice() <= maxPrice)
	            .toList();
	    }

	    model.addAttribute("productList", productList);
	    model.addAttribute("name", name);
	    model.addAttribute("minPrice", minPrice);
	    model.addAttribute("maxPrice", maxPrice);

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
		
		return "redirect:/admin";
	}
}
