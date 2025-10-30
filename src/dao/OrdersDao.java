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
import model.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class OrdersDao {
     private String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    private String dbUsername = "root";
    private String dbPasswd = "gadiella";
    
     public int createOrder(Orders order) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "INSERT INTO orders (customer_id, order_date, status) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, order.getCustomerId());
                pst.setTimestamp(2, new Timestamp(order.getOrderDate().getTime()));
                pst.setString(3, order.getStatus());

                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
     
      // READ operation: Get an order by ID
    public Orders getOrderById(int orderId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Orders order = new Orders();
                order.setOrderId(rs.getInt("order_id"));
                order.setCustomerId(rs.getInt("customer_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setStatus(rs.getString("status"));
                return order;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int updateOrder(int orderId, Timestamp orderDate, String status) {
    int result = 0;
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "UPDATE orders SET order_date = ?, status = ? WHERE order_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setTimestamp(1, orderDate);
        pst.setString(2, status);
        pst.setInt(3, orderId);

        result = pst.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
}


    // UPDATE operation: Update an existing order
   public boolean updateOrder(Orders order) {
    boolean updated = false;
    try 
       (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)){
        String sql = "UPDATE orders SET customer_id=?, order_date=?, status=? WHERE order_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, order.getCustomerId());
        pstmt.setTimestamp(2, order.getOrderDate());
        pstmt.setString(3, order.getStatus());
        pstmt.setInt(4, order.getOrderId());

        int rowsAffected = pstmt.executeUpdate();  // 🔥
        if (rowsAffected > 0) {
            updated = true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return updated;
}


    // DELETE operation: Delete an order
    public int deleteOrder(int orderId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Orders> findOrdersByCustomerId(int customerId) {
        List<Orders> orders = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM orders WHERE customer_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, customerId);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    orders.add(new Orders(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getTimestamp("order_date"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }
    
    
    public List<Orders> findAllOrders() {
    List<Orders> orders = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM orders";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                orders.add(new Orders(
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getTimestamp("order_date"),
                    rs.getString("status")
                ));
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return orders;
}
    public List<Orders> findOrdersByDateRange(Timestamp start, Timestamp end) {
    List<Orders> orders = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM orders WHERE order_date BETWEEN ? AND ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setTimestamp(1, start);
        pst.setTimestamp(2, end);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            orders.add(new Orders(
                rs.getInt("order_id"),
                rs.getInt("customer_id"),
                rs.getTimestamp("order_date"),
                rs.getString("status")
            ));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return orders;
}
public List<Orders> findOrdersByStatus(String status) {
    List<Orders> orders = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM orders WHERE status = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, status);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            orders.add(new Orders(
                rs.getInt("order_id"),
                rs.getInt("customer_id"),
                rs.getTimestamp("order_date"),
                rs.getString("status")
            ));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return orders;
}

 public List<Integer> getAllOrderIds() {
        List<Integer> orderIds = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT order_id FROM orders"; // Only select IDs
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                orderIds.add(rs.getInt("order_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orderIds;
    }
 
 public List<Orders> getAllOrders() {
    List<Orders> ordersList = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM orders";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Orders order = new Orders();
            order.setOrderId(rs.getInt("order_id"));
            order.setCustomerId(rs.getInt("customer_id"));
            order.setOrderDate(rs.getTimestamp("order_date"));
            order.setStatus(rs.getString("status"));

            ordersList.add(order);
        }

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ordersList;
}
 
 // Inside OrdersDao.java
public int addOrder(Orders order) {
    int row = 0;
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "INSERT INTO orders (customer_id, order_date, status) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, order.getCustomerId());
        pst.setTimestamp(2, order.getOrderDate());
        pst.setString(3, order.getStatus());

        row = pst.executeUpdate(); // returns number of affected rows
    } catch (Exception e) {
        e.printStackTrace();
    }
    return row; // row > 0 means success
}
 public List<Orders> searchOrdersByCustomerName(String keyword) {
    List<Orders> ordersList = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT o.* FROM orders o JOIN customers c ON o.customer_id = c.customer_id WHERE c.name LIKE ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        
        stmt.setString(1, "%" + keyword + "%");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Orders order = new Orders();
            order.setOrderId(rs.getInt("order_id"));
            order.setCustomerId(rs.getInt("customer_id"));
            order.setOrderDate(rs.getTimestamp("order_date"));
            order.setStatus(rs.getString("status"));
            ordersList.add(order);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ordersList;
}



}
