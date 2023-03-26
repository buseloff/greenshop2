package com.busel.spring.shop_rest.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
    public Category() {
    }
    public Category(String title) {
        this.title = title;
          }

    public Category(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

   public void addProductToCategory(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", products=" + products +
                '}';
    }
}

