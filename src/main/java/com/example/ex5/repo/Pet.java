/**
 * This is the pet class
 */

package com.example.ex5.repo;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    @SequenceGenerator(name = "pet_seq", sequenceName = "pet_sequence", allocationSize = 1, initialValue = 1000)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Name needs to have characters")
    private String name;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PetVisit> adoptionMeetings = new HashSet<>();

    @NotEmpty(message = "Description is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Description needs to have characters")
    private String description;

    @NotEmpty(message = "Breed is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Breed needs to have characters")
    private String breed;
    private String gender;

    @Positive(message = "Age must be positive")
    @NotNull(message = "Age is mandatory")
    private int age;

    @NotEmpty(message = "Image is mandatory")
    @Pattern(regexp = ".*\\S.*", message = "Image needs to have characters")
    private String image;

    private String type;

////////////////

    // constructors:
    public Pet() {}

    public Pet(String name, String desc, String breed, String gender, int age, String image, String type) {
        this.name = name;
        this.description = desc;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.image = image;
        this.type = type;
    }

    /////////////////

    // getters and setters:

    public String getImage() {
        return image;
    }

    public void setImage(String imgSrc) {
        this.image = imgSrc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String userName) {
        this.name = userName;
    }
    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() { return description; }

    public String getBreed() {
        return breed;
    }

    public String getGender() {
        return gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
