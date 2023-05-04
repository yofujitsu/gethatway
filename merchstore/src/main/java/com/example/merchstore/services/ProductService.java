package com.example.merchstore.services;

import com.example.merchstore.DAO.models.Image;
import com.example.merchstore.DAO.models.Product;
import com.example.merchstore.DAO.models.User;
import com.example.merchstore.repositories.ProductRepository;
import com.example.merchstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private List<Product> products = new ArrayList<>();

    public  List<Product> listProducts(String title, String category){
        if (title!=null) {
            List<Product> target = productRepository.findByTitle(title);
            if(category!=null) {
                List<Product> res = new ArrayList<>();
                ListIterator<Product> lItr = target.listIterator();
                while(lItr.hasNext()) {
                    Product item = lItr.next();
                    if(item.getCategory().equals(category)) {
                        res.add(item);
                    }
                }
                return res;
            }
            return productRepository.findByTitle(title);
        }
        else if (category!=null)
            return productRepository.findByCategory(category);
        return productRepository.findAll();
    }
    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0){
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0){
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDB = (Product) productRepository.save(product);
        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId());
        productRepository.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
    }


    public Product getProductById(Long id) {
       return productRepository.findById(id).orElse(null);
    }

}