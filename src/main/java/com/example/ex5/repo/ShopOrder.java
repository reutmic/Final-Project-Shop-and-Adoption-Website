/**
 * This is the Shop orders class
 */
package com.example.ex5.repo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;


@Entity
public class ShopOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int orderNum;
    private String status;
    private String address;
    private String clientName;
    private String clientContacts;


    ////////////////

    // ==== functions ====

    // constructors:

    /**
     * This is a constructor
     */
    public ShopOrder() {

    }


    /**
     *
     * @param orderNum - the order's number
     * @param status - order's status
     * @param address - client's address
     * @param clientName - client's name
     * @param clientContacts - client's contacts information provided - a string of email and phone
     */
    public ShopOrder(int orderNum, String status, String address, String clientName,
                     String clientContacts) {
        this.orderNum = orderNum;
        this.status = status;
        this.address = address;
        this.clientName = clientName;
        this.clientContacts = clientContacts;
    }

    //////


    // getters and setters:
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getClientContacts() {
        return clientContacts;
    }

    public void setClientContacts(String clientContacts) {
        this.clientContacts = clientContacts;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
