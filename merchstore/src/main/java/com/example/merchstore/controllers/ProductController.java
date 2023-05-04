package com.example.merchstore.controllers;

import com.example.merchstore.DAO.models.Product;
import com.example.merchstore.DAO.models.User;
import com.example.merchstore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/apparel")
    public String products(@RequestParam(name = "searchWord", required = false) String title, @RequestParam(name = "searchType", required = false) String category, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title, category));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        model.addAttribute("searchType", category);
        return "products";
    }

    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "main";
    }


    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        Product product = productService.getProductById(id);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("category", product.getCategory());
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product, Principal principal, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3")  MultipartFile file3 ) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/my/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/my/products";
    }

    @GetMapping("/my/products")
    public String userProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "my-products";
    }

}
