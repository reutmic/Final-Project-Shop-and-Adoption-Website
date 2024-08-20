/**
 * This is a class for user details
 */

package com.example.ex5.SessionClasses;

import com.example.ex5.ValidationGroups.AdvancedUserInfo;
import com.example.ex5.ValidationGroups.BasicUserInfo;
import jakarta.validation.constraints.*;

public class UserDetails {
    @NotEmpty(message = "First name is mandatory", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    @Pattern(regexp = ".*\\S.*", message = "First name needs to have characters", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    private String firstName;
    @NotEmpty(message = "Last name is mandatory", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    @Pattern(regexp = ".*\\S.*", message = "Last name needs to have characters", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    private String lastName;
    @Pattern(regexp = "^\\d{2}-\\d{7}$|^\\d{3}-\\d{7}$", message = "Phone number must be in the format XX-XXXXXXX or XXX-XXXXXXX", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    private String phone;
    @NotEmpty(message = "Email is mandatory", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    @Email(message = "Email should be valid", groups = {BasicUserInfo.class, AdvancedUserInfo.class})
    private String email;
    @NotEmpty(message = "City is mandatory", groups = AdvancedUserInfo.class)
    @Pattern(regexp = ".*\\S.*", message = "City needs to have characters", groups = AdvancedUserInfo.class)
    private String city;
    @NotEmpty(message = "Street is mandatory", groups = AdvancedUserInfo.class)
    @Pattern(regexp = ".*\\S.*", message = "Street needs to have characters", groups = AdvancedUserInfo.class)
    private String street;
    @NotNull(message = "House number is mandatory", groups = AdvancedUserInfo.class)
    @Positive(message = "House number cannot be negative or 0", groups = AdvancedUserInfo.class)
    private int houseNum;
    @NotNull(message = "Zip code is mandatory", groups = AdvancedUserInfo.class)
    @Positive(message = "Zip code cannot be negative or 0", groups = AdvancedUserInfo.class)
    private int zipCode;


    /////////////////

    // constructors:
    public UserDetails(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }


    public UserDetails() {

    }

    //////////////////////

    // getters and setters:

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

}
