/**
 * This is a controller for all the user's actions (/user/..) regarding checkout and orders.
 */

package com.example.ex5.Controllers;

import com.example.ex5.Services.OrderService;
import com.example.ex5.ValidationGroups.AdvancedUserInfo;
import com.example.ex5.SessionClasses.Cart;
import com.example.ex5.SessionClasses.UserDetails;
import com.example.ex5.repo.*;
import com.example.ex5.repo.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;

@Controller
public class UserOrderController {

    @Autowired
    private ShopOrderRepository orders;

    @Autowired
    private ProductRepository products;

    @Autowired
    @Qualifier("sessionCart")
    private Cart cart;

    @Autowired
    @Qualifier("sessionUserDetails")
    private UserDetails sessionUserDetails;

    @Autowired
    private OrderService orderService;

    /////////////////////

    // ===== Mappings =====

    /**
     * POST mapping for when the user submits the oreder's number to get an order's status
     * @param orderNum - order's number
     * @param model
     * @param principal
     * @return - redirects to the '/user/track/{ordernum}' url
     */
    @PostMapping("/user/track")
    public String trackOrder(@RequestParam("orderNum") int orderNum, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());
        return "redirect:/user/track/" + orderNum;
    }

    ///////////

    /**
     * GET mapping for the 'track-order' page.
     * @param principal
     * @param model
     * @return - returns the 'track-order' page
     */
    @GetMapping("/user/track")
    public String displayTrackOrderForm(Principal principal, Model model) {
        model.addAttribute("currUser", principal.getName());

        return "userCartOrder/track-order";
    }

    ///////////

    /**
     * GET mapping for the order status page.
     * @param orderNum - number of the order
     * @param model
     * @param principal
     * @return - returns the page 'order-status'
     */
    @GetMapping("/user/track/{orderNum}")
    public String showOrderStatus(@PathVariable("orderNum") int orderNum, Model model, Principal principal) {
        List<ShopOrder> findOrders = orders.findByOrderNum(orderNum);
        model.addAttribute("currUser", principal.getName());
        if (findOrders.isEmpty()) {
            model.addAttribute("message", "Order not found");
        } else {
            ShopOrder order = findOrders.get(0); // Assuming order numbers are unique, we take the first result
            model.addAttribute("order", order);
        }
        return "userCartOrder/order-status";
    }

    ///////////

    /**
     * GET mapping for the checkout page
     * @param model
     * @param principal
     * @return - returns the 'check-out' page.
     */
    @GetMapping("/user/checkout")
    public String displayAddressForm(Model model, Principal principal) {
        model.addAttribute("userDetails", sessionUserDetails);
        model.addAttribute("currUser", principal.getName());
        model.addAttribute("cart", cart);

        ArrayList<String> removedProducts= new ArrayList<>();
        model.addAttribute("removedProducts", removedProducts);

        // if the cart is empty, do not allow to do checkout:
        if (cart.getCart().size() == 0) {
            return "redirect:/user/cart";
        }

        return "userCartOrder/check-out";
    }

    ///////////

    /**
     * GET mapping for the '/user/orderconfirmed' page
     * @param model
     * @param principal
     * @return - returns the 'order-placed' page
     */
    @GetMapping("/user/orderconfirmed")
    public String completeOrderWithNoParameters(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        if (!model.containsAttribute("order")) {
            return "redirect:/user/cart";
        }

        return "userCartOrder/order-placed";
    }

    ///////////

    /**
     * POST mapping for when the user submits the checkout form.
     * @param userDetails - validating the user details submitted
     * @param result
     * @param redirectAttributes
     * @param model
     * @param principal
     * @return - returns the '/user/orderconfirmed' page
     */
    @PostMapping("/user/orderconfirmed")
//    public String showNewOrderDetails(@Validated(AdvancedUserInfo.class) UserDetails userDetails,
    public ModelAndView showNewOrderDetails(@Validated(AdvancedUserInfo.class) UserDetails userDetails,
                                            BindingResult result, RedirectAttributes redirectAttributes,
                                            Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        // checking the user details validation:
        if (result.hasErrors()) {
            System.out.println("validation errors: " + result.getAllErrors());
            return new ModelAndView("userCartOrder/check-out", model.asMap());
        }

        // saving the user details in the session:
        sessionUserDetails.setFirstName(userDetails.getFirstName());
        sessionUserDetails.setLastName(userDetails.getLastName());
        sessionUserDetails.setPhone(userDetails.getPhone());
        sessionUserDetails.setEmail(userDetails.getEmail());
        sessionUserDetails.setCity(userDetails.getCity());
        sessionUserDetails.setStreet(userDetails.getStreet());
        sessionUserDetails.setHouseNum(userDetails.getHouseNum());
        sessionUserDetails.setZipCode(userDetails.getZipCode());

       /* checking if any of the products in the cart was deleted from the shop or
       run out of stock before creating a new order:
       */
        ArrayList<String> removedProducts = new ArrayList<>();      // for out of stock products
        ArrayList<String> deletedProducts = new ArrayList<>();      // for deleted products

        for (Iterator<Product> iterator = cart.getCart().iterator(); iterator.hasNext();) {
            Product cartProduct = iterator.next();
            Optional<Product> optionalShopProduct = products.findById(cartProduct.getId());

            // if the product is not deleted:
            if (optionalShopProduct.isPresent()) {
                Product shopProduct = optionalShopProduct.get();

                // checking if the product is still in stock:
                if (cartProduct.getQuantity() > shopProduct.getStock()) {
                    cart.updateTotalPrice();
                    removedProducts.add(cartProduct.getProdName());
                }
            }
            // if the product has been deleted from the shop:
            else {
                deletedProducts.add(cartProduct.getProdName());
                iterator.remove();  // removes the product from the cart
            }
        }

        model.addAttribute("deletedProducts", deletedProducts);
        model.addAttribute("removedProducts", removedProducts);
        model.addAttribute("cart", cart);

        // returns to the cart page if the order could not be completed:
        if (!deletedProducts.isEmpty()) {
            return new ModelAndView("userCartOrder/cart", model.asMap());
        }

        if (!removedProducts.isEmpty()) {
            for (Product product : cart.getCart()) {
                Product shopProduct = products
                        .findById(product.getId())
                        .orElseThrow(
                                () -> new IllegalArgumentException("Invalid product Id:" + product.getId())
                        );

                product.setStock(shopProduct.getStock());
            }
            return new ModelAndView("userCartOrder/cart", model.asMap());
        }


        // if the order can be completed, create a new order:
        String clientName = sessionUserDetails.getFirstName() + " " + sessionUserDetails.getLastName();
        String clientContacts = "Phone: " + sessionUserDetails.getPhone() + ", Email: " + sessionUserDetails.getEmail();
        String address = sessionUserDetails.getHouseNum() + " " + sessionUserDetails.getStreet() + "street" + ", " + sessionUserDetails.getCity();

        // generating a random order number:
        int orderNum = orderService.generateUniqueOrderNum();
//        Random random = new Random();
//        int orderNum = 10000000 + random.nextInt(90000000);

        ShopOrder order = new ShopOrder(orderNum, "Placed", address, clientName,
                clientContacts);

        // saving the order:
        orders.save(order);

        // after an order has been created, reduce the stock of the products by their quantity in the cart:
        for (Product cartProduct : cart.getCart()) {
            Product shopProduct = products
                    .findById(cartProduct.getId())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Invalid product Id:" + cartProduct.getId())
                    );
            shopProduct.setStock(shopProduct.getStock() - cartProduct.getQuantity());
            products.save(shopProduct);
        }

        // clearing  the cart after completing an order:
        cart.clearCart();

        redirectAttributes.addFlashAttribute("order", order);
        return new ModelAndView(new RedirectView("/user/orderconfirmed"));
    }

}
