/**
 * This is a controller for all the user's actions (/user/..) regarding the cart.
 */

package com.example.ex5.Controllers;

import com.example.ex5.SessionClasses.Cart;
import com.example.ex5.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class UserCartController {

    @Autowired
    @Qualifier("sessionCart")
    private Cart cart;

    @Autowired
    private ProductRepository products;

    /////////////////////

    // ===== Mappings =====

    /**
     * POST mapping for the 'add to cart' action
     * @param id - id of the product
     * @param model
     * @param principal
     * @return - redirects to the shop page
     */
    @PostMapping("/user/addtocart")
    public String addToCart(@RequestParam("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        Product product = products
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid product Id:" + id)
                );

        // checking if the product already exists in the cart, and if so, increasing its quantity:
        boolean productExists = false;

        for (Product cartProduct : cart.getCart()) {
            if (cartProduct.getId() == (product.getId())) {
                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            product.setQuantity(1);
            cart.add(product);
        }

        cart.updateTotalPrice();

        model.addAttribute("cart", cart);
        model.addAttribute("products", products.findAll());

        return "redirect:/shared/shop";
    }

    ///////////

    /**
     * GET mapping for the cart page
     * @param model
     * @param principal
     * @return - returns the 'cart' page
     */
    @GetMapping("/user/cart")
    public String displayCart(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        // check for any deleted or out of stock products and update the cart:
        ArrayList<String> removedProducts = new ArrayList<>();
        ArrayList<String> deletedProducts = new ArrayList<>();

        for (Iterator<Product> iterator = cart.getCart().iterator(); iterator.hasNext();) {
            Product cartProduct = iterator.next();
            Optional<Product> optionalShopProduct = products.findById(cartProduct.getId());

            if (optionalShopProduct.isPresent()) {
                Product shopProduct = optionalShopProduct.get();

                cartProduct.setProdName(shopProduct.getProdName());
                cartProduct.setStock(shopProduct.getStock());
                cartProduct.setCategory(shopProduct.getCategory());
                cartProduct.setSuitedFor(shopProduct.getSuitedFor());
                cartProduct.setDescription(shopProduct.getDescription());
                cartProduct.setPrice(shopProduct.getPrice());
                cartProduct.setImage(shopProduct.getImage());
            }
            else {
                deletedProducts.add(cartProduct.getProdName());
                iterator.remove(); // removes the deleted product from the cart
            }
        }

        model.addAttribute("removedProducts", removedProducts);
        model.addAttribute("deletedProducts", deletedProducts);
        model.addAttribute("cart", cart);

        return "userCartOrder/cart";
    }

    ///////////

    /**
     * POST mapping for the removal of a product from the cart.
     * @param id
     * @param model
     * @param principal
     * @return - redirects to the 'cart' page
     */
    @PostMapping("/user/removefromcart")
    public String removeProductFromCart(@RequestParam("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        Product product = products
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid product Id:" + id)
                );

        cart.removeProduct(product);
        cart.updateTotalPrice();
        model.addAttribute("cart", cart);

        return "redirect:/user/cart";
    }
}
