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
import model.OrderItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsDao {
    
     private String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    private String dbUsername = "root";
    private String dbPasswd = "gadiella";
    
     public int addOrderItem(OrderItems orderItem) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "INSERT INTO order_items (order_id, item_id, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, orderItem.getOrderId());
                pst.setInt(2, orderItem.getItemId());
                pst.setInt(3, orderItem.getQuantity());

                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
      public int updateOrderItem(OrderItems orderItem) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "UPDATE order_items SET item_id = ?, quantity = ? WHERE order_item_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderItem.getItemId());
            pst.setInt(2, orderItem.getQuantity());
            pst.setInt(3, orderItem.getOrderItemId());

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // DELETE operation: Delete an order item
    public int deleteOrderItem(int orderItemId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "DELETE FROM order_items WHERE order_item_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderItemId);

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    } 
    
     // READ operation: Get all order items for an order
    public List<OrderItems> getOrderItemsByOrderId(int orderId) {
        List<OrderItems> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM order_items WHERE order_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                OrderItems orderItem = new OrderItems();
                orderItem.setOrderItemId(rs.getInt("order_item_id"));
                orderItem.setOrderId(rs.getInt("order_id"));
                orderItem.setItemId(rs.getInt("item_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                list.add(orderItem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
     // 4. Get all OrderItems
    public List<OrderItems> getAllOrderItems() {
        List<OrderItems> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items";
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                OrderItems item = new OrderItems();
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setItemId(rs.getInt("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<OrderItems> searchOrderItemsByItemName(String keyword) {
    List<OrderItems> list = new ArrayList<>();
    String sql = "SELECT oi.* FROM order_items oi JOIN menu_items mi ON oi.item_id = mi.item_id WHERE mi.name LIKE ?";

    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd);
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setString(1, "%" + keyword + "%");
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            OrderItems item = new OrderItems();
            item.setOrderItemId(rs.getInt("order_item_id"));
            item.setOrderId(rs.getInt("order_id"));
            item.setItemId(rs.getInt("item_id"));
            item.setQuantity(rs.getInt("quantity"));
            list.add(item);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
