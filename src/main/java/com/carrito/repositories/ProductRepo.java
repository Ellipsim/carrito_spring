package com.carrito.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrito.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
