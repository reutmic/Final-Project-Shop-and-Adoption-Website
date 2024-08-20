/**
 * This is a PetVisit class
 */

package com.example.ex5.repo;

import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.persistence.Entity;

@Entity
public class PetVisit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "dog_id", nullable = false)
    private Pet pet;

    private String visitDate;
    private String potentialAdopterName;
    private String potentialAdopterPhone;
    private String potentialAdopterEmail;
    private String potentialAdopterAbout;
    private String status;
    private String adminComment;

    /////////////////////


    // constructors:
    public PetVisit(Pet pet, String potentialAdopterName, String potentialAdopterPhone, String potentialAdopterEmail, String visitDate, String potentialAdopterAbout) {
        this.pet = pet;
        this.visitDate = visitDate;
        this.potentialAdopterName = potentialAdopterName;
        this.potentialAdopterPhone = potentialAdopterPhone;
        this.potentialAdopterEmail = potentialAdopterEmail;
        this.potentialAdopterAbout = potentialAdopterAbout;
        this.status = "Pending";
        this.adminComment="";
    }

    public PetVisit() {

    }

    /////////////

    // getters and setters:

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }

    public String getPotentialAdopterName() {
        return potentialAdopterName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String meetingDateTime) {
        this.visitDate = meetingDateTime;
    }

    public void setPotentialAdopterName(String potentialAdopterName) {
        this.potentialAdopterName = potentialAdopterName;
    }

    public String getPotentialAdopterPhone() {
        return potentialAdopterPhone;
    }

    public void setPotentialAdopterPhone(String potentialAdopterPhone) {
        this.potentialAdopterPhone = potentialAdopterPhone;
    }

    public String getPotentialAdopterEmail() {
        return potentialAdopterEmail;
    }

    public void setPotentialAdopterEmail(String potentialAdopterEmail) {
        this.potentialAdopterEmail = potentialAdopterEmail;
    }

    public String getPotentialAdopterAbout() {
        return potentialAdopterAbout;
    }

    public void setPotentialAdopterAbout(String potentialAdopterAbout) {
        this.potentialAdopterAbout = potentialAdopterAbout;
    }
}
