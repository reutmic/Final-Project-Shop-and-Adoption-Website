/**
 * This is a controller for all the admin's actions (/admin/..) regarding handling products.
 */

package com.example.ex5.Controllers;

import com.example.ex5.repo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminProductsController {

    @Autowired
    private ProductRepository products;

    /////////////////////

    // ===== Mappings =====

    /**
     * POST mapping for when the admin submits the form to update a product
     * @param id - the updated product's id
     * @param product - to check that the product is valid
     * @param result - for validation
     * @param model
     * @param principal
     * @return - redirects to the shop page
     */
    @PostMapping("/admin/updateproduct/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result,
                                Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        if (result.hasErrors()) {
            product.setId(id);
            return "admin/update-product";
        }

        products.save(product);
        model.addAttribute("products", products.findAll());
        return "redirect:/shared/shop";
    }

    ///////////

    /**
     * GET mapping for the '/admin/updateproduct'
     * @return - redirects to the shop page
     */
    @GetMapping("/admin/updateproduct")
    public String updateProductGetNoParams() {
        return "redirect:/shared/shop";
    }

    ///////////

    /**
     * GET method for when the admin clicks on 'update' of one of the products
     * @param id
     * @param model
     * @param principal
     * @return - returns the 'update-product' page (a page with update product form)
     */
    @GetMapping("/admin/updateproduct/{id}")
    public String displayUpdateProductForm(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        Product product = products.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("product", product);
        return "admin/update-product";
    }

    ///////////

    /**
     * GET mapping for the url '/admin/deleteproduct'
     * @param model
     * @param principal
     * @return - returns the shop page
     */
    @GetMapping("/admin/deleteproduct")
    public String getDeleteProduct(Model model, Principal principal) {
        model.addAttribute("products", products.findAll());
        model.addAttribute("currUser", principal.getName());

        return "sharedPages/shop-products";
    }

    ///////////

    /**
     * POST mapping for deleting a product
     * @param id - the product's id
     * @param model
     * @param principal
     * @return - redirects to the shop page
     */
    @PostMapping("/admin/deleteproduct")
    public String deleteProduct(@RequestParam("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        Product product = products
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid product Id:" + id)
                );

        products.delete(product);
        model.addAttribute("products", products.findAll());

        return "redirect:/shared/shop";
    }

    ///////////

    /**
     * GET mapping for the add product form page - when the admin clicks on 'add product'
     * @param model
     * @param principal
     * @return - returns the 'add-product' page
     */
    @GetMapping("/admin/registerproduct")
    public String displayNewProductForm(Product product, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());
        return "admin/add-product";
    }

    ///////////

    /**
     * POST mapping for adding a product - when the admin submits the 'add product' form.
     * @param product - the new product
     * @param result - to check the product's validation
     * @param model
     * @param principal
     * @return - redirects to the shop page
     */
    @PostMapping("/admin/addproduct")
    public String addProduct(@Valid Product product, BindingResult result, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        if (result.hasErrors()) {
            System.out.println("validation errors: " + result.getAllErrors());
            return "admin/add-product";
        }

        products.save(product);
        model.addAttribute("products", products.findAll());

        return "redirect:/shared/shop";
    }

    ///////////

    /**
     * GET mapping for url '/admin/addproduct'
     * @param model
     * @param principal
     * @return - redirects to the shop page
     */
    @GetMapping("/admin/addproduct")
    public String getAddProductForm(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        return "redirect:/shared/shop";
    }
}
