package com.example.merchstore;

import com.example.merchstore.DAO.models.Cart;
import com.example.merchstore.DAO.models.Product;
import com.example.merchstore.repositories.CartRepository;
import com.example.merchstore.repositories.ProductRepository;
import com.example.merchstore.services.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    private CartService cartService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartService(cartRepository, productRepository);
    }

    @Test
    void testAddToCart() {
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setPrice(100);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Cart cart = new Cart();
        cart.setProductId(productId);
        when(cartRepository.findByProductId(productId)).thenReturn(null);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        cartService.addToCart(productId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testRemoveFromCart() {
        Long productId = 1L;
        Cart cart = new Cart();
        cart.setQuantity(2);
        when(cartRepository.findByProductId(productId)).thenReturn(cart);
        cartService.removeFromCart(productId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testGetCheckoutPrice() {
        Long productId1 = 1L;
        Long productId2 = 2L;
        Product product1 = new Product();
        product1.setId(productId1);
        product1.setPrice(100);
        Product product2 = new Product();
        product2.setId(productId2);
        product2.setPrice(200);
        Cart cart1 = new Cart();
        cart1.setCurr_product(product1);
        cart1.setQuantity(2);
        Cart cart2 = new Cart();
        cart2.setCurr_product(product2);
        cart2.setQuantity(1);
        List<Cart> cartList = Arrays.asList(cart1, cart2);
        when(cartRepository.findAll()).thenReturn(cartList);
        int expectedSum = 300;
        int actualSum = cartService.getCheckoutPrice();
        assertEquals(expectedSum, actualSum);
    }
}
