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

import model.Customers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
     private String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    private String dbUsername = "root";
    private String dbPasswd = "gadiella";
    
     public int registerCustomer(Customers customer) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "INSERT INTO customers (name, phone, email, gender) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, customer.getName());
                pst.setString(2, customer.getPhone());
                pst.setString(3, customer.getEmail());
                pst.setString(4, customer.getGender());

                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int updateCustomer(Customers customer) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "UPDATE customers SET name = ?, phone = ?, email = ?, gender = ? WHERE customer_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, customer.getName());
                pst.setString(2, customer.getPhone());
                pst.setString(3, customer.getEmail());
                pst.setString(4, customer.getGender());
                pst.setInt(5, customer.getCustomerId());

                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int deleteCustomer(int customerId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "DELETE FROM customers WHERE customer_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, customerId);
                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public Customers findCustomerById(int customerId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM customers WHERE customer_id = ?";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, customerId);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    return new Customers(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("gender")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Customers> findAllCustomers() {
        List<Customers> customers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM customers";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    customers.add(new Customers(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("gender")
                    ));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }
    
    public List<Customers> getAllCustomers() {
    List<Customers> customers = new ArrayList<>();
    
    String sql = "SELECT customer_id, name FROM customers";
    
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd);
         PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        while (rs.next()) {
            Customers customer = new Customers();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            customers.add(customer);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return customers;
}
public Customers getCustomerById(int customerId) {
    Customers customer = null;

    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)){
        String sql = "SELECT * FROM customers WHERE customer_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, customerId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            customer = new Customers();
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            // Set other fields if needed
        }

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return customer;
}

}
    
    
