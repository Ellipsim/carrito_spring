package com.carrito.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carrito.services.DatabaseExportService;
import com.carrito.services.ProductService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private final ProductService productService; 
	private final DatabaseExportService databaseExportService;

	public AdminController(ProductService productService, DatabaseExportService databaseExportService) {
		
		this.productService = productService;
		this.databaseExportService = databaseExportService;
	}
	
    @GetMapping
    public String admin(Model model) {
    	
    	model.addAttribute("productList", productService.getAllProducts());
    	
        return "admin";
    }
    
    @GetMapping("/export")
    public ResponseEntity<byte[]> downloadDatabase() {

        byte[] sql = databaseExportService.exportDatabaseToSql();

        return ResponseEntity.ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"productos.sql\""
            )
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(sql);
    }
}