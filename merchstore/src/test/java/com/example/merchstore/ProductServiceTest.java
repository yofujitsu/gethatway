package com.example.merchstore;

import com.example.merchstore.DAO.models.Product;
import com.example.merchstore.DAO.models.User;
import com.example.merchstore.repositories.ProductRepository;
import com.example.merchstore.repositories.UserRepository;
import com.example.merchstore.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productRepository, userRepository);
    }

    @Test
    void testListProducts() {
        String title = "Test Product";
        String category = "Test Category";
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setTitle(title);
        product.setCategory(category);
        productList.add(product);
        when(productRepository.findByTitle(title)).thenReturn(productList);
        List<Product> result = productService.listProducts(title, category);
        assertEquals(1, result.size());
        assertEquals(title, result.get(0).getTitle());
        assertEquals(category, result.get(0).getCategory());
    }

    @Test
    void testSaveProduct() throws IOException {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return email;
            }
        };
        Product product = new Product();
        MultipartFile file1 = mock(MultipartFile.class);
        MultipartFile file2 = mock(MultipartFile.class);
        MultipartFile file3 = mock(MultipartFile.class);
//        productService.saveProduct(principal, product, file1, file2, file3);
//        verify(productRepository, times(2)).save(any(Product.class));
    }

    @Test
    void testGetUserByPrincipal() {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);
        Principal principal = new Principal() {
            @Override
            public String getName() {
                return email;
            }
        };
        User result = productService.getUserByPrincipal(principal);
        assertEquals(email, result.getEmail());
    }

    @Test
    void testDeleteProduct() {
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        productService.deleteProduct(id);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testGetProductById() {
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Product result = productService.getProductById(id);
        assertEquals(id, result.getId());
    }
}
