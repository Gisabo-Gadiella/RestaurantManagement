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

import model.Payments;

import java.sql.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class PaymentsDao {
    
      private String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    private String dbUsername = "root";
    private String dbPasswd = "gadiella";
    
     public int addPayment(Payments payment) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
           String sql = "INSERT INTO payments (order_id, payment_date, amount_paid, payment_method, payment_status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
               pst.setInt(1, payment.getOrderId());
                pst.setTimestamp(2, payment.getPaymentDate()); // <-- Set the datetime
                pst.setBigDecimal(3, payment.getAmountPaid());
                pst.setString(4, payment.getPaymentMethod());
                pst.setString(5, payment.getPaymentStatus());


                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
     
      // READ operation: Get payment by order id
    public Payments getPaymentByOrderId(int orderId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM payments WHERE order_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Payments payment = new Payments();
                payment.setPaymentId(rs.getInt("payment_id"));
                payment.setOrderId(rs.getInt("order_id"));
                payment.setPaymentDate(rs.getTimestamp("payment_date"));
                payment.setAmountPaid(rs.getBigDecimal("amount_paid"));
                payment.setPaymentMethod(rs.getString("payment_method"));
                payment.setPaymentStatus(rs.getString("payment_status"));
                return payment;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // UPDATE operation: Update a payment
    public int updatePayment(Payments payment) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "UPDATE payments SET payment_date = ?, amount_paid = ?, payment_method = ?, payment_status = ? WHERE payment_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, payment.getPaymentDate());
            pst.setBigDecimal(2, payment.getAmountPaid());
            pst.setString(3, payment.getPaymentMethod());
            pst.setString(4, payment.getPaymentStatus());
            pst.setInt(5, payment.getPaymentId());

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // DELETE operation: Delete a payment
    public int deletePayment(int paymentId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "DELETE FROM payments WHERE payment_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, paymentId);

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    } 
    
    
    // GET ALL payments (optionally by status)
public List<Payments> getAllPayments(String statusFilter) {
    List<Payments> payments = new ArrayList<>();
    String sql;
    
    if ("All".equalsIgnoreCase(statusFilter)) {
        sql = "SELECT * FROM payments";
    } else {
        sql = "SELECT * FROM payments WHERE payment_status = ?";
    }
    
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd);
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        if (!"All".equalsIgnoreCase(statusFilter)) {
            pst.setString(1, statusFilter);
        }
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Payments payment = new Payments();
            payment.setPaymentId(rs.getInt("payment_id"));
            payment.setOrderId(rs.getInt("order_id"));
            payment.setPaymentDate(rs.getTimestamp("payment_date"));
            payment.setAmountPaid(rs.getBigDecimal("amount_paid"));
            payment.setPaymentMethod(rs.getString("payment_method"));
            payment.setPaymentStatus(rs.getString("payment_status"));
            payments.add(payment);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return payments;
}

   private BigDecimal getTotalAmountForOrder(int orderId) {
    BigDecimal totalAmount = BigDecimal.ZERO;
    String sql = "SELECT SUM(mi.price * oi.quantity) AS total_amount " +
                 "FROM order_items oi " +
                 "JOIN menu_items mi ON oi.item_id = mi.item_id " +
                 "WHERE oi.order_id = ?";

    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd);
         PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setInt(1, orderId);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            totalAmount = rs.getBigDecimal("total_amount");
            if (totalAmount == null) {
                totalAmount = BigDecimal.ZERO;
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return totalAmount;
}

}
