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
public class ComboItem {
     private int id;
    private String label;

    public ComboItem(int id, String label) {
        this.id = id;
        this.label = label;
    }

     public int getId() {
        return id;
    }
     
      public String toString() {
        return label; // what will show in the JComboBox
    }
     
     
    
}
