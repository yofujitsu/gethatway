package com.example.merchstore.services;

import com.example.merchstore.DAO.models.Cart;
import com.example.merchstore.DAO.models.Product;
import com.example.merchstore.repositories.CartRepository;
import com.example.merchstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final ProductRepository productRepository;


    public void addToCart(Long id) {
        Cart cartForCheck = cartRepository.findByProductId(id);
        Cart cartItem = new Cart();
        if(cartForCheck == null) {
            cartItem.setProductId(id);
            Product pr = productRepository.findById(id).orElse(null);
            cartItem.setCurr_product(pr);
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartRepository.save(cartItem);
        }
        else {
            cartForCheck.setQuantity(cartForCheck.getQuantity()+1);
            cartRepository.save(cartForCheck);
        }
    }

    public void removeFromCart(Long id) {
        Cart cart = cartRepository.findByProductId(id);
        if (cart != null) {
            if(cart.getQuantity() == 1)
                cartRepository.delete(cart);
            else if(cart.getQuantity() > 1) {
                cart.setQuantity(cart.getQuantity()-1);
                cartRepository.save(cart);
            }
        }
    }

    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    public int getCheckoutPrice() {
        int sum = 0;
        List<Cart> allCart = getCart();
        for(Cart item : allCart) {
            sum+=item.getCurr_product().getPrice();
        }
        return sum;
    }
}
