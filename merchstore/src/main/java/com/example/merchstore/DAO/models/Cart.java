package com.example.merchstore.DAO.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity")
    private int quantity = 0;

    @Column(name = "productId")
    private Long productId;

    @ManyToOne
    @JoinColumn()
    private Product curr_product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getCurr_product() {
        return curr_product;
    }

    public void setCurr_product(Product curr_product) {
        this.curr_product = curr_product;
    }
}
