package com.example.merchstore.controllers;

import com.example.merchstore.DAO.models.Cart;
import com.example.merchstore.DAO.models.User;
import com.example.merchstore.repositories.CartRepository;
import com.example.merchstore.services.CartService;
import com.example.merchstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final CartService cartService;
    private final CartRepository cartRepository;


    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, Principal principal, Model model, HttpServletRequest request) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        cartService.addToCart(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        List<Cart> cartItems = cartService.getCart();
        model.addAttribute("cart", cartItems);
        int allPrice = cartService.getCheckoutPrice();
        model.addAttribute("cartPrice", allPrice);
        return "cart";
    }

    @PostMapping("/cart/order")
    public String createOrder(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        List<Cart> cartItems = cartService.getCart();
        model.addAttribute("cart", cartItems);
        cartRepository.deleteAll(cartItems);
        return "redirect:/order";
    }

    @GetMapping("/order")
    public String successOrder(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "order";
    }

}