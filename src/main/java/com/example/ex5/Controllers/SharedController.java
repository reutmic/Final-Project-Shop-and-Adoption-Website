/**
 * This is a controller for all the GET mappings for the shared pages ('/shared/,,')
 */

package com.example.ex5.Controllers;

import com.example.ex5.Services.ProductService;
import com.example.ex5.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
public class SharedController {

    @Autowired
    private PetRepository pets;

    @Autowired
    private ProductRepository products;

    @Autowired
    private ProductService productService;

    /////////////////////

    // ===== Mappings =====

    /**
     * GET mapping for the landing page
     * @param model
     * @param principal
     * @return - returns the home page
     */
    @GetMapping("/")
    public String main(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        return "sharedPages/home-page";
    }

    ///////////

    /**
     * GET mapping for the 'adopt' page
     * @param model
     * @param principal
     * @return - returns the 'adopt' page
     */
    @GetMapping("/shared/adopt")
    public String displayForAdoptionPage(Model model, Principal principal) {
        model.addAttribute("pets", pets.findAll());
        model.addAttribute("currUser", principal.getName());

        return "sharedPages/adopt";
    }

    ///////////

    /**
     * GET mapping for displaying cats for adoption in the 'adopt' page
     * @param model
     * @param principal
     * @return - returns the 'adopt' page
     */
    @GetMapping("/shared/adopt/cats")
    public String displayCats(Model model, Principal principal) {
        model.addAttribute("pets", pets.findByType("Cat"));
        model.addAttribute("currUser", principal.getName());
        return "sharedPages/adopt";
    }

    ///////////

    /**
     * GET mapping for displaying dogs for adoption in the 'adopt' page
     * @param model
     * @param principal
     * @return - returns the 'adopt' page
     */
    @GetMapping("/shared/adopt/dogs")
    public String displayDogs(Model model, Principal principal) {
        model.addAttribute("pets", pets.findByType("Dog"));
        model.addAttribute("currUser", principal.getName());
        return "sharedPages/adopt";
    }

    ///////////

    /**
     * GET mapping for the shop page
     * @param model
     * @param principal
     * @return - returns the 'shop-products' page
     */
    @GetMapping("/shared/shop")
    public String displayProducts(Model model, Principal principal) {
        model.addAttribute("products", products.findAll());
        model.addAttribute("currUser", principal.getName());

        return "sharedPages/shop-products";
    }

    ///////////

    /**
     * POST mapping for searching products in the shop by using filters.
     * @param model
     * @param principal
     * @param suitedFor
     * @param categories
     * @return - returns the 'shop-products' page
     */
    @PostMapping("/shared/shop")
    public String displayFilteredProducts(Model model, Principal principal,
                                          @RequestParam(value="suitedFor", required=false) List<String> suitedFor,
                                          @RequestParam(value="category", required=false) List<String> categories)
    {
        model.addAttribute("currUser", principal.getName());

        List<Product> filteredProducts = productService.getFilteredProducts(suitedFor, categories);
        model.addAttribute("products", filteredProducts);


        if (suitedFor != null && !suitedFor.isEmpty()) {
            model.addAttribute("suitedFor", suitedFor);
        }

        if (categories != null && !categories.isEmpty()) {
            model.addAttribute("categories", categories);
        }

        return "sharedPages/shop-products";
    }

    ///////////

//    @PostMapping("/admin/updateproduct/{id}")
//    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result,
//                                Model model, Principal principal)
//    {
//        model.addAttribute("currUser", principal.getName());
//
//        if (result.hasErrors()) {
//            product.setId(id);
//            return "admin/update-product";
//        }
//
//        products.save(product);
//        model.addAttribute("products", products.findAll());
//        return "redirect:/shared/shop";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/updateproduct")
//    public String updateProductGetNoParams() {
//        return "redirect:/shared/shop";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/updateproduct/{id}")
//    public String displayUpdateProductForm(@PathVariable("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        Product product = products.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("product", product);
//        return "admin/update-product";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/deleteproduct")
//    public String getDeleteProduct(Model model, Principal principal) {
//        model.addAttribute("products", products.findAll());
//        model.addAttribute("currUser", principal.getName());
//
//        return "sharedPages/shop-products";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/deleteproduct")
//    public String deleteProduct(@RequestParam("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        Product product = products
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid product Id:" + id)
//                );
//
//        products.delete(product);
//        model.addAttribute("products", products.findAll());
//
//        return "redirect:/shared/shop";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/registerproduct")
//    public String displayNewProductForm(Product product, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//        return "admin/add-product";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/addproduct")
//    public String addProduct(@Valid Product product, BindingResult result, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        // validate the object and get the errors
//        if (result.hasErrors()) {
//            // errors MUST be displayed in the view and not just printed to the console
//            System.out.println("validation errors: " + result.getAllErrors());
//            return "admin/add-product";
//        }
//
//        products.save(product);
//
//        model.addAttribute("products", products.findAll());
//
//        return "redirect:/shared/shop";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/addproduct")
//    public String showAddProductForm(Product product, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        return "redirect:/shared/shop";
//    }

    ///////////

//    @PostMapping("/admin/addpet")
//    public String addPet(@Valid Pet pet, BindingResult result, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        if (result.hasErrors()) {
//            System.out.println("validation errors: " + result.getAllErrors());
//            return "admin/add-pet";
//        }
//
//        pets.save(pet);
//        model.addAttribute("pets", pets.findAll());
//
//        return "redirect:/shared/adopt";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/registerpet")
//    public String displayNewForAdoptionForm(Pet pet, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//        return "admin/add-pet";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/addpet")
//    public String showAddPetForm(Pet pet, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        return "redirect:/shared/adopt";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/updatepet/{id}")
//    public String updatePet(@PathVariable("id") long id, @Valid Pet pet, BindingResult result,
//                            Model model, Principal principal)
//    {
//        model.addAttribute("currUser", principal.getName());
//
//        if (result.hasErrors()) {
//            return "admin/update-pet";
//        }
//
//        pets.save(pet);
//        model.addAttribute("pets", pets.findAll());
//        return "redirect:/shared/adopt";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/updatepet/{id}")
//    public String displayUpdatePetForm(@PathVariable("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        Pet pet = pets.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("Invalid pet Id:" + id));
//
//        model.addAttribute("pet", pet);
//        return "admin/update-pet";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/updatepet")
//    public String updatePetGetNoParams() {
//        return "redirect:/shared/adopt";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/removepet")
//    public String getRemovePet(Model model, Principal principal) {
//        model.addAttribute("pets", pets.findAll());
//        model.addAttribute("currUser", principal.getName());
//
//        return "redirect:/shared/adopt";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/removepet")
//    public String removePet(@RequestParam("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        Pet pet = pets
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid pet Id:" + id)
//                );
//
//        pets.delete(pet);
//        model.addAttribute("pets", pets.findAll());
//
//        return "redirect:/shared/adopt";
//    }

    ///////////

//    @PostMapping("/user/addtocart")
//    public String addToCart(@RequestParam("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        Product product = products
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid product Id:" + id)
//                );
//
//        boolean productExists = false;
//
//        for (Product cartProduct : cart.getCart()) {
//            if (cartProduct.getId() == (product.getId())) {
//                cartProduct.setQuantity(cartProduct.getQuantity() + 1);
//                productExists = true;
//                break;
//            }
//        }
//
//        if (!productExists) {
//            product.setQuantity(1); // Set initial quantity to 1 when adding a new product
//            cart.add(product);
//        }
//
//        cart.updateTotalPrice();
//
//        model.addAttribute("cart", cart);
//        model.addAttribute("products", products.findAll());
//
//        return "redirect:/shared/shop";
//    }
//
//    ///////////
//
//    @GetMapping("/user/cart")
//    public String displayCart(Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//        ArrayList<String> removedProducts = new ArrayList<>();
//        ArrayList<String> deletedProducts = new ArrayList<>();
//
//        for (Iterator<Product> iterator = cart.getCart().iterator(); iterator.hasNext();) {
//            Product cartProduct = iterator.next();
//            Optional<Product> optionalShopProduct = products.findById(cartProduct.getId());
//
//            if (optionalShopProduct.isPresent()) {
//                Product shopProduct = optionalShopProduct.get();
//
//                cartProduct.setProdName(shopProduct.getProdName());
//                cartProduct.setStock(shopProduct.getStock());
//                cartProduct.setCategory(shopProduct.getCategory());
//                cartProduct.setSuitedFor(shopProduct.getSuitedFor());
//                cartProduct.setDescription(shopProduct.getDescription());
//                cartProduct.setPrice(shopProduct.getPrice());
//                cartProduct.setImage(shopProduct.getImage());
//            } else {
//                deletedProducts.add(cartProduct.getProdName());
//                iterator.remove(); // remove deleted product from cart
//            }
//        }
//
//        model.addAttribute("removedProducts", removedProducts);
//        model.addAttribute("deletedProducts", deletedProducts);
//        model.addAttribute("cart", cart);
//
//        return "userCartOrder/cart";
//    }
//
//    ///////////
//
//    @PostMapping("/user/removefromcart")
//    public String removeProductFromCart(@RequestParam("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//        ArrayList<String> removedProducts = new ArrayList<>();
//
//        Product product = products
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid product Id:" + id)
//                );
//
//        model.addAttribute("removedProducts", removedProducts);
//
//        cart.removeProduct(product);
//        cart.updateTotalPrice();
//        model.addAttribute("cart", cart);
//
//        return "redirect:/user/cart";
//    }

    ///////////

//    @GetMapping("/admin/manageorders")
//    public String displayOrdersManagement(Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//        model.addAttribute("orders", orders.findAll());
//
//        return "admin/manage-orders";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/changeorderstatus")
//    public String changeOrderStatus(@RequestParam("status") String status,
//                                    @RequestParam("id") long id, Model model, Principal principal)
//    {
//        model.addAttribute("currUser", principal.getName());
//
//        ShopOrder order = orders
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid product Id:" + id)
//                );
//
//        order.setStatus(status);
//        orders.save(order);
//
//        return "redirect:/admin/manageorders";
//    }

    ///////////

//    @GetMapping("/user/checkvisits")
//    public String displayCheckVisitsForm(Principal principal, Model model) {
//        model.addAttribute("currUser", principal.getName());
//
//        return "userPetVisits/check-visits";
//    }
//
//    ///////////
//
//    @PostMapping("/user/checkvisits")
//    public String checkVisits(@RequestParam("email") String email, Model model, Principal principal) {
//        List<String> excludedStatuses = Arrays.asList("Cancelled", "Done");
//
//        model.addAttribute("currUser", principal.getName());
//        model.addAttribute("userVisits", petVisits.findByPotentialAdopterEmailAndStatusNotIn(email, excludedStatuses));
////        model.addAttribute("userVisits", petVisits.findByPotentialAdopterEmail(email));
//        model.addAttribute("userEmail", email);
//
//
//        return "userPetVisits/visits-status";
//    }
//
//    ///////////
//
//    @PostMapping("/user/cancelvisit")
//    public String cancelVisit(@RequestParam("email") String email, @RequestParam("id") long id,
//                              Model model, Principal principal)
//    {
//        model.addAttribute("currUser", principal.getName());
//
//        List<String> excludedStatuses = Arrays.asList("Cancelled", "Done");
//
//        // we throw an exception if the user is not found
//        // since we don't catch the exception here, it will fall back on an error page (see below)
//        PetVisit visit = petVisits
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid visit Id:" + id)
//                );
//
//        visit.setStatus("Cancelled");
//        petVisits.save(visit);
//        model.addAttribute("userVisits", petVisits.findByPotentialAdopterEmailAndStatusNotIn(email, excludedStatuses));
////        model.addAttribute("userVisits", petVisits.findByPotentialAdopterEmail(email));
//
//        return "userPetVisits/visits-status";
//    }

    ///////////

//    @GetMapping("/admin/managevisits")
//    public String displayVisitsManagement(Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//        model.addAttribute("visits", petVisits.findAll());
//
//        return "admin/manage-pet-visits";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/changevisitstatus")
//    public String changeVisitStatus(@RequestParam("status") String status, @RequestParam("comment") String comment,
//                                    @RequestParam("id") long id, Model model, Principal principal)
//    {
//        model.addAttribute("currUser", principal.getName());
//
//        PetVisit visit = petVisits
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid visit Id:" + id)
//                );
//
//        visit.setStatus(status);
//        visit.setAdminComment(comment);
//        petVisits.save(visit);
//
//        return "redirect:/admin/managevisits";
//    }
//
//    ///////////
//
//    @PostMapping("/admin/deletevisit")
//    public String deleteVisit(@RequestParam("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        // we throw an exception if the user is not found
//        // since we don't catch the exception here, it will fall back on an error page (see below)
//        PetVisit visit = petVisits
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid visit Id:" + id)
//                );
//
//        petVisits.delete(visit);
//        model.addAttribute("visits", petVisits.findAll());
//
//        return "redirect:/admin/managevisits";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/deletevisit/{id}")
//    public String deleteVisitGet(@PathVariable long id) {
//        return "redirect:/admin/managevisits";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/deletevisit")
//    public String deleteVisitGetNoParams() {
//        return "redirect:/admin/managevisits";
//    }

    ///////////

//    @PostMapping("/admin/deleteorder")
//    public String deleteOrder(@RequestParam("id") long id, Model model, Principal principal) {
//        model.addAttribute("currUser", principal.getName());
//
//        ShopOrder order = orders
//                .findById(id)
//                .orElseThrow(
//                        () -> new IllegalArgumentException("Invalid order Id:" + id)
//                );
//
//        orders.delete(order);
//        model.addAttribute("orders", orders.findAll());
//
//        return "redirect:/admin/manageorders";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/deleteorder/{id}")
//    public String deleteOrderGet(@PathVariable long id) {
//        return "redirect:/admin/manageorders";
//    }
//
//    ///////////
//
//    @GetMapping("/admin/deleteorder")
//    public String deleteOrderGetNoParams() {
//        return "redirect:/admin/manageorders";
//    }

    ///////////

    /**
     * This is for displaying an error page.
     * @return - returns the error page
     */
    @RequestMapping("/shared/403")
    public String displayErrorPage() {
        return "sharedPages/403";
    }

    ///////////



}