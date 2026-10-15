package com.carrito.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	
	// NOTE: Lombok estaba causando muchos problemas en la generación de las funciones,
	// por lo que se ha eliminado del código
	public Product() {
		
	}
	
	public Long getId() {
		
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		
		this.name = name;
	}

	public double getPrice() {
		
		return price;
	}

	public void setPrice(double price) {
		
		this.price = price;
	}
	
}
