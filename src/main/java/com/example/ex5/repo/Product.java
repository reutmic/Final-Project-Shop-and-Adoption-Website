/**
 * This is the product class
 */

package com.example.ex5.repo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_sequence", allocationSize = 1, initialValue = 1000)
    private long id;

    @NotEmpty(message = "Product name is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Name needs to have characters")
    private String prodName;

    @NotEmpty(message = "Product description is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Description needs to have characters")
    private String description;

    private String category;

    @PositiveOrZero(message = "Price must be positive or zero")
    @NotNull(message = "Price is mandatory")
    private int price;

    private String suitedFor;       // for which animal the product is suited

    @PositiveOrZero(message = "Stock must be positive or zero")
    @NotNull(message = "Stock is mandatory")
    private int stock;

    private int quantity;

    @NotEmpty(message = "Image is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Image needs to have characters")
    private String image;

    ////////////////

    // constructors:

    public Product() {}

    public Product(String name, String category, String suitedFor, int price, int stock, String description, String image) {
        this.prodName = name;
        this.category = category;
        this.suitedFor = suitedFor;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.quantity = 1;
        this.image = image;
    }

    ////////////////

    // getters and setters:
    public int getPrice() {
        return price;
    }
    public void setPrice(int age) {
        this.price = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imgSrc) {
        this.image = imgSrc;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setProdName(String name) {
        this.prodName = name;
    }
    public String getProdName() {
        return prodName;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() { return category; }

    public String getSuitedFor() {
        return suitedFor;
    }

    public void setSuitedFor(String suitedFor) {
        this.suitedFor = suitedFor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //////////////

    //functions:

    /**
     * This function returns the calculated price of the product by price*quantity of the product.
     * @return - returns the calculated price
     */
    public double getPriceWithQuantity() {
        return quantity * price;
    }

    ///////////////

    // for comparing between two products:

    /**
     * This is for being able to compare between two products
     * @param o - a product
     * @return - returns a boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
