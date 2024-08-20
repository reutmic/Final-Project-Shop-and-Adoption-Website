/**
 * This is a controller for all the admin's actions (/admin/..) regarding handling pets for adoption.
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
public class AdminPetsController {

    @Autowired
    private PetRepository pets;

    /////////////////////

    // ===== Mappings =====

    /**
     * POST mapping for adding a new pet - the submission of the 'add-pet' form
     * @param pet - the new pet
     * @param result - for validation
     * @param model
     * @param principal
     * @return - redirects to the 'adopt' page
     */
    @PostMapping("/admin/addpet")
    public String addPet(@Valid Pet pet, BindingResult result, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        if (result.hasErrors()) {
            System.out.println("validation errors: " + result.getAllErrors());
            return "admin/add-pet";
        }

        pets.save(pet);
        model.addAttribute("pets", pets.findAll());

        return "redirect:/shared/adopt";
    }

    ///////////

    /**
     * GET mapping for the add pet form page - when the admin clicks on 'add pet'
     * @param model
     * @param principal
     * @return - returns the 'add-pet' page
     */
    @GetMapping("/admin/registerpet")
    public String displayNewForAdoptionForm(Pet pet, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());
        return "admin/add-pet";
    }

    ///////////

    /**
     * GET mapping for '/admin/addpet' url
     * @param model
     * @param principal
     * @return - redirects to the 'adopt' page
     */
    @GetMapping("/admin/addpet")
    public String getAddPetForm(Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        return "redirect:/shared/adopt";
    }

    ///////////

    /**
     * POST mapping for submitting the update-pet form
     * @param id - the updated pet id
     * @param pet - the pet
     * @param result - for validation
     * @param model
     * @param principal
     * @return - redirects to the 'adopt' page
     */
    @PostMapping("/admin/updatepet/{id}")
    public String updatePet(@PathVariable("id") long id, @Valid Pet pet, BindingResult result,
                            Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        if (result.hasErrors()) {
            return "admin/update-pet";
        }

        pets.save(pet);
        model.addAttribute("pets", pets.findAll());
        return "redirect:/shared/adopt";
    }

    ///////////

    /**
     * GET mapping for the update-pet form page - when the admin clicks on 'update'
     * @param id
     * @param model
     * @param principal
     * @return - returns the 'update-pet' page
     */
    @GetMapping("/admin/updatepet/{id}")
    public String displayUpdatePetForm(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        Pet pet = pets.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid pet Id:" + id));

        model.addAttribute("pet", pet);
        return "admin/update-pet";
    }

    ///////////

    /**
     * GET mapping for '/admin/updatepet' url
     * @return - redirects to the 'adopt' page
     */
    @GetMapping("/admin/updatepet")
    public String updatePetGetNoParams() {
        return "redirect:/shared/adopt";
    }

    ///////////

    /**
     * GET mapping for the '/admin/removepet' url
     * @param model
     * @param principal
     * @return - redirects to the 'adopt' page
     */
    @GetMapping("/admin/removepet")
    public String getRemovePet(Model model, Principal principal) {
        model.addAttribute("pets", pets.findAll());
        model.addAttribute("currUser", principal.getName());

        return "redirect:/shared/adopt";
    }

    ///////////

    /**
     * POST mapping for removing a pet
     * @param id - the pet's id
     * @param model
     * @param principal
     * @return - redirects to the 'adopt' page
     */
    @PostMapping("/admin/removepet")
    public String removePet(@RequestParam("id") long id, Model model, Principal principal) {
        model.addAttribute("currUser", principal.getName());

        Pet pet = pets
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid pet Id:" + id)
                );

        pets.delete(pet);
        model.addAttribute("pets", pets.findAll());

        return "redirect:/shared/adopt";
    }
}
