/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gisabo
 */
public class Customers {
    
    private int customerId;
    private String name;
    private String phone;
    private String email;
    private String gender;

    public Customers() {
    }
    
       public Customers(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        
    }
       

    public Customers(int customerId, String name, String phone, String email, String gender) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
  @Override
public String toString() {
    return this.name; // or getName(), depending on your class
}
    
}
