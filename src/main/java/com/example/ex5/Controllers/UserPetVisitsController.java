/**
 * This is a controller for all the user's actions (/user/..) regarding scheduling pet visits.
 */

package com.example.ex5.Controllers;

import com.example.ex5.ValidationGroups.BasicUserInfo;
import com.example.ex5.SessionClasses.UserDetails;
import com.example.ex5.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserPetVisitsController {

    @Autowired
    private PetRepository pets;

    @Autowired
    private PetVisitRepository petVisits;

    @Autowired
    @Qualifier("sessionUserDetails")
    private UserDetails sessionUserDetails;


    /////////////////////

    // ===== Mappings =====


    /**
     * This is a GET mapping when the user clicks on "Let's Meet" in the adoption page.
     * It returns the page with the form to schedule a visit.
     * @param id - the pet id
     * @param model
     * @param principal
     * @return - the page with the form to schedule a visit
     */
    @GetMapping("/user/meet/{id}")
    public String displayMeetingForm(@PathVariable("id") long id, Model model, Principal principal) {

        Pet pet = pets.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("pet", pet);
        model.addAttribute("currUser", principal.getName());
        model.addAttribute("userDetails", sessionUserDetails);
        model.addAttribute("emptyDate", "false");
        return "userPetVisits/schedule-visit";
    }

    ///////////

    /**
     * get method for url /user/schedulevisit
     * @param model
     * @param principal
     * @return - redirects to the adoption page
     */
    @GetMapping("/user/schedulevisit")
    public String getScheduleVisit(Model model, Principal principal) {
        model.addAttribute("pets", pets.findAll());
        model.addAttribute("currUser", principal.getName());

        return "redirect:/shared/adopt";
    }

    ///////////

    /**
     * post mapping for when the user submits the "schedule visit" form.
     * @param userDetails - the user's details
     * @param result - to check validation of the user's details
     * @param date - the date submitted
     * @param about - the 'about' field submitted
     * @param petId - the chosen pet's id
     * @param model
     * @param principal
     * @return - redirects to the 'visit-received' page
     */
    @PostMapping("/user/schedulevisit")
    public String showVisitDetails(@Validated(BasicUserInfo.class) UserDetails userDetails,
                                   BindingResult result,
                                   @RequestParam("date") String date,
                                   @RequestParam("about") String about,
                                   @RequestParam("petId") long petId,
                                   Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        Pet pet = pets.findById(petId).orElseThrow(
                () -> new IllegalArgumentException("Invalid pet Id:" + petId));
        model.addAttribute("pet", pet);

        // checks the validation of the user details:
        if (result.hasErrors()) {
            System.out.println("validation errors: " + result.getAllErrors());
            return "userPetVisits/schedule-visit";
        }

        // saves user details in the session:
        sessionUserDetails.setFirstName(userDetails.getFirstName());
        sessionUserDetails.setLastName(userDetails.getLastName());
        sessionUserDetails.setPhone(userDetails.getPhone());
        sessionUserDetails.setEmail(userDetails.getEmail());

        // check date validation:
        if (date == "") {
            model.addAttribute("userDetails", sessionUserDetails);
            model.addAttribute("emptyDate", "true");
            return "userPetVisits/schedule-visit";
        }

        // creating and saving the new visit request:
        String potentialAdopterName = userDetails.getFirstName() + " " + userDetails.getLastName();
        PetVisit visit = new PetVisit(pet, potentialAdopterName, userDetails.getPhone(),
                userDetails.getEmail(), date, about);
        petVisits.save(visit);

        return "redirect:/user/visitreceived";
    }

    ///////////

    /**
     * get method for url /user/visitreceived
     * @return - returns the 'visit-received' page
     */
    @GetMapping("/user/visitreceived")
    public String visitReceived() {
        return "userPetVisits/visit-received";
    }

    ///////////

    /**
     * GET mapping for the 'check-visits' page
     * @param principal
     * @param model
     * @return - returns the 'check-visits' page
     */
    @GetMapping("/user/checkvisits")
    public String displayCheckVisitsForm(Principal principal, Model model) {
        model.addAttribute("currUser", principal.getName());

        return "userPetVisits/check-visits";
    }

    ///////////

    /**
     * POST mapping for submitting the form to check pet visits by email
     * @param email - the submitted email address
     * @param model
     * @param principal
     * @return - returns the 'visits-status' page
     */
    @PostMapping("/user/checkvisits")
    public String checkVisits(@RequestParam("email") String email, Model model, Principal principal) {
        List<String> excludedVisitsStatuses = Arrays.asList("Cancelled", "Done");

        model.addAttribute("currUser", principal.getName());
        // displaying relevant visits:
        model.addAttribute("userVisits", petVisits.findByPotentialAdopterEmailAndStatusNotIn(email, excludedVisitsStatuses));
        model.addAttribute("userEmail", email);

        return "userPetVisits/visits-status";
    }

    ///////////

    /**
     * POST mapping for canceling a visit - when the user clicks on 'cancel':
     * @param email - the user's email
     * @param id - the visit's id
     * @param model
     * @param principal
     * @return - returns the 'visits-status' page
     */
    @PostMapping("/user/cancelvisit")
    public String cancelVisit(@RequestParam("email") String email, @RequestParam("id") long id,
                              Model model, Principal principal)
    {
        model.addAttribute("currUser", principal.getName());

        List<String> excludedStatuses = Arrays.asList("Cancelled", "Done");

        PetVisit visit = petVisits
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid visit Id:" + id)
                );

        visit.setStatus("Cancelled");
        petVisits.save(visit);
        model.addAttribute("userVisits", petVisits.findByPotentialAdopterEmailAndStatusNotIn(email, excludedStatuses));

        return "userPetVisits/visits-status";
    }

}
