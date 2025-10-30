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
public class MenuItems {
    
     private int itemId;
    private String name;
    private String description;
    private double price;
    private boolean availability;
     private String category;

    public MenuItems() {
    }

    public MenuItems(int itemId, String name, String description, double price, boolean availability, String category) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.category = category;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
     public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
