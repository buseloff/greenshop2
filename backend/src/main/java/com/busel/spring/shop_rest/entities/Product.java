package com.busel.spring.shop_rest.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@Entity
@Table(name = "products")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Lob
    //@Type(type="org.hibernate.type.BinaryType")
    @Column(name = "image_data")
    private byte[] imageData;

    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private Float quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    public Product() {
    }

    public Product(String name, byte[] imageData, Float price, Float quantity) {
        this.name = name;
        this.imageData = imageData;
        this.price = price;
        this.quantity = quantity;
    }

   public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageData='" + imageData + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}

