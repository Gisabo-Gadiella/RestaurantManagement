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
public class CombItem {
    private int id;
    private String value;

    public CombItem(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return value;
    }
}


