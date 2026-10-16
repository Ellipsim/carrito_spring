package com.carrito.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carrito.models.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUsernameOrderByProduct_NameAsc(String username);

    Optional<CartItem> findByUsernameAndProduct_Id(
            String username, Long productId);

    void deleteByUsernameAndProduct_Id(
            String username, Long productId);
    
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.product.id = :productId")
    void deleteByProductId(@Param("productId") Long productId);
}