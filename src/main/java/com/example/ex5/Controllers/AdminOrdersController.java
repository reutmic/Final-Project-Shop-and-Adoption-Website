/**
 * This is a controller for all the admin's actions (/admin/..) regarding orders.
 */

package com.example.ex5.Controllers;

import com.example.ex5.repo.*;
import com.example.ex5.repo.ShopOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminOrdersController {

    @Autowired
    private ShopOrderRepository orders;

    /////////////////////

    // ===== Mappings =====

    /**
     * GET mapping for the 'manage-orders' page
     * @param model
     * @param principal
     * @return - returns the 'manage-orders' page
     */
    @GetMapping("/admin/manageorders")
    public String displayOrdersManagement(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());
        model.addAttribute("orders", orders.findAll());

        return "admin/manage-orders";
    }

    ///////////

    /**
     * POST mapping for changing an order's status
     * @param status - the order's status
     * @param id - the order's id
     * @param model
     * @param principal
     * @return - redirects to the 'manage-orders' page
     */
    @PostMapping("/admin/changeorderstatus")
    public String changeOrderStatus(@RequestParam("status") String status,
                                    @RequestParam("id") long id, Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        ShopOrder order = orders
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid product Id:" + id)
                );

        order.setStatus(status);
        orders.save(order);

        return "redirect:/admin/manageorders";
    }

    ///////////

    /**
     * POST mapping for deleting an order
     * @param id - the order's id
     * @param model
     * @param principal
     * @return - redirects to the 'manage-orders' page
     */
    @PostMapping("/admin/deleteorder")
    public String deleteOrder(@RequestParam("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        ShopOrder order = orders
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid order Id:" + id)
                );

        orders.delete(order);
        model.addAttribute("orders", orders.findAll());

        return "redirect:/admin/manageorders";
    }

    ///////////

    /**
     * GET mapping for the '/admin/deleteorder/{id}' url
     * @param id
     * @return - redirects to the 'manage-orders' page
     */
    @GetMapping("/admin/deleteorder/{id}")
    public String deleteOrderGet(@PathVariable long id) {
        return "redirect:/admin/manageorders";
    }

    ///////////

    /**
     * GET mapping for the '/admin/deleteorder' url
     * @return - redirects to the 'manage-orders' page
     */
    @GetMapping("/admin/deleteorder")
    public String deleteOrderGetNoParams() {
        return "redirect:/admin/manageorders";
    }

}
