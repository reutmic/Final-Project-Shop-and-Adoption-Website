/**
 * This is the cart class.
 */

package com.example.ex5.SessionClasses;

import com.example.ex5.repo.Product;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;



@Component
public class Cart implements Serializable {
    private ArrayList<Product> cart;
    private double totalPrice = 0;

    ////////////


    // constructors:

    /**
     * This is a constructor
     */
    public Cart() {
        this.cart = new ArrayList<>();
    }

    //////////

    // getters and setters:
    public ArrayList<Product>  getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product>  products) {
        this.cart = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //////////

    /**
     * This function adds a product to the cart
     * @param product
     */
    public void add (Product product) {
        cart.add(product);
    }

    /////////////

    /**
     * This function removes a product from the cart.
     * @param product
     */
    public void removeProduct (Product product) {
        if (cart.remove(product)) {
            totalPrice -= product.getPrice();
        }
    }

    ////////////

    /**
     * This function clears the cart
     */
    public void clearCart() {
        cart.clear();
        totalPrice = 0;
    }

    ////////////

    /**
     * This function updates the total price of the products in the cart
     */
    public void updateTotalPrice() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPriceWithQuantity();
        }

        setTotalPrice(total);
    }
}



