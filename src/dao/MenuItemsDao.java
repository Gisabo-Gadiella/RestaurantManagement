/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Gisabo
 */
import model.MenuItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsDao {
     private String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    private String dbUsername = "root";
    private String dbPasswd = "gadiella";
    
     public int addMenuItem(MenuItems menuItem) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "INSERT INTO menu_items (name, description, price, availability) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, menuItem.getName());
                pst.setString(2, menuItem.getDescription());
                pst.setDouble(3, menuItem.getPrice());
                pst.setBoolean(4, menuItem.isAvailability());

                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<MenuItems> getAllMenuItems() {
        List<MenuItems> menuItems = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM menu_items";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    menuItems.add(new MenuItems(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getBoolean("availability"),
                        rs.getString("category")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return menuItems;
    }
    
    public int updateMenuItem(MenuItems item) {
    int rowsAffected = 0;
    try 
        (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "UPDATE menu_items SET name = ?, description = ?, price = ?, availability = ?, category = ? WHERE item_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, item.getName());
        pst.setString(2, item.getDescription());
        pst.setDouble(3, item.getPrice());
        pst.setBoolean(4, item.isAvailability());
        pst.setString(5, item.getCategory());
        pst.setInt(6, item.getItemId());
        
        rowsAffected = pst.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rowsAffected;
}
    
    public int deleteMenuItem(int itemId) {
    int rowsAffected = 0;
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        
        String sql = "DELETE FROM menu_items WHERE item_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, itemId);
        
        rowsAffected = pst.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rowsAffected;
}


}
