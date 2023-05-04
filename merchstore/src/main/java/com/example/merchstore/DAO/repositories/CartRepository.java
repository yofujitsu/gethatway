package com.example.merchstore.repositories;

import com.example.merchstore.DAO.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByProductId(Long productId);
}
