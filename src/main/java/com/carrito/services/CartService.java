package com.carrito.services;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carrito.models.CartItem;
import com.carrito.models.Product;
import com.carrito.repositories.CartItemRepo;

@Service
public class CartService {

    private final CartItemRepo repository;

    public CartService(CartItemRepo repository) {
        this.repository = repository;
    }

    @Transactional
    public void addProduct(Product product) {
        String username = getUsername();

        CartItem item = repository.findByUsernameAndProduct_Id(username, product.getId()).orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            repository.save(item);
        } else {
            item = new CartItem();
            item.setUsername(username);
            item.setProduct(product);
            item.setQuantity(1);
            repository.save(item);
        }
    }

    @Transactional
    public void deleteProduct(Long productId) {
        String username = getUsername();

        CartItem item = repository
                .findByUsernameAndProduct_Id(username, productId)
                .orElse(null);

        if (item == null) {
            return;
        }

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            repository.save(item);
        } else {
            repository.delete(item);
        }
    }

    @Transactional
    public void deleteAllByProductId(Long id) {
        repository.deleteByUsernameAndProduct_Id(getUsername(), id);
    }
    
    private String getUsername() {
        return SecurityContextHolder.getContext()
                .getAuthentication().getName();
    }

    public List<Product> getProducts() {
        return repository
                .findByUsernameOrderByProduct_NameAsc(getUsername())
                .stream()
                .map(CartItem::getProduct)
                .toList();
    }
    
    public List<CartItem> getCartItems() {
        return repository.findByUsernameOrderByProduct_NameAsc(getUsername());
    }
}