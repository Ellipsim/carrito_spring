package com.carrito.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carrito.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUsernameOrderByProduct_NameAsc(String username);

    Optional<CartItem> findByUsernameAndProduct_Id(
            String username, Long productId);

    void deleteByUsernameAndProduct_Id(
            String username, Long productId);
}