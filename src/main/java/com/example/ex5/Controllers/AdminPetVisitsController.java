/**
 * This is a controller for all the admin's actions (/admin/..) regarding handling pet visits.
 */

package com.example.ex5.Controllers;

import com.example.ex5.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminPetVisitsController {

    @Autowired
    private PetVisitRepository petVisits;

    /////////////////////

    // ===== Mappings =====

    /**
     * GET mapping for the 'manage-visits' page.
     * @param model
     * @param principal
     * @return - returns the 'manage-visits' page.
     */
    @GetMapping("/admin/managevisits")
    public String displayVisitsManagement(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());
        model.addAttribute("visits", petVisits.findAll());

        return "admin/manage-pet-visits";
    }

    ///////////

    /**
     * POST mapping for changing a visit's status
     * @param status - the updates visit's status
     * @param comment - the admin's comment
     * @param id - the visit's id
     * @param model
     * @param principal
     * @return - redirects to the 'manage-visits' page
     */
    @PostMapping("/admin/changevisitstatus")
    public String changeVisitStatus(@RequestParam("status") String status, @RequestParam("comment") String comment,
                                    @RequestParam("id") long id, Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        PetVisit visit = petVisits
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid visit Id:" + id)
                );

        visit.setStatus(status);
        visit.setAdminComment(comment);
        petVisits.save(visit);

        return "redirect:/admin/managevisits";
    }

    ///////////

    /**
     * POST mapping for deleting a visit
     * @param id - the visit's id
     * @param model
     * @param principal
     * @return - redirects to the 'manage-visits' page
     */
    @PostMapping("/admin/deletevisit")
    public String deleteVisit(@RequestParam("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        PetVisit visit = petVisits
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid visit Id:" + id)
                );

        petVisits.delete(visit);
        model.addAttribute("visits", petVisits.findAll());

        return "redirect:/admin/managevisits";
    }

    ///////////

    /**
     * GET mapping for the '/admin/deletevisit/{id}' url
     * @param id
     * @return - redirects to the 'manage-visits' page
     */
    @GetMapping("/admin/deletevisit/{id}")
    public String deleteVisitGet(@PathVariable long id) {
        return "redirect:/admin/managevisits";
    }

    ///////////

    /**
     * GET mapping for the '/admin/deletevisit/ url
     * @return - redirects to the 'manage-visits' page
     */
    @GetMapping("/admin/deletevisit")
    public String deleteVisitGetNoParams() {
        return "redirect:/admin/managevisits";
    }

}
