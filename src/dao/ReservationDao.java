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
import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import model.Customers;


public class ReservationDao {
     private String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    private String dbUsername = "root";
    private String dbPasswd = "gadiella";
    
     public int createReservation(Reservation reservation) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "INSERT INTO reservations (customer_id, reservation_datetime, party_size, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, reservation.getCustomerId());
                pst.setTimestamp(2, new Timestamp(reservation.getReservationDatetime().getTime()));
                pst.setInt(3, reservation.getPartySize());
                pst.setString(4, reservation.getStatus());

                return pst.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
       public int updateReservation(Reservation reservation) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "UPDATE reservations SET customer_id = ?, reservation_datetime = ?, party_size = ?, status = ? WHERE reservation_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, reservation.getCustomerId());
            pst.setTimestamp(2, reservation.getReservationDatetime());
            pst.setInt(3, reservation.getPartySize());
            pst.setString(4, reservation.getStatus());
            pst.setInt(5, reservation.getReservationId());

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // DELETE operation (Delete a reservation)
    public int deleteReservation(int reservationId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "DELETE FROM reservations WHERE reservation_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, reservationId);

            return pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
     
      public Reservation getReservationById(int reservationId) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
            String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, reservationId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setReservationDatetime(rs.getTimestamp("reservation_datetime"));
                reservation.setPartySize(rs.getInt("party_size"));
                reservation.setStatus(rs.getString("status"));
                return reservation;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
      

 public List<Reservation> findAllReservations() {
    List<Reservation> reservations = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM reservations";
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                // Fetching reservation details
                int reservationId = rs.getInt("reservation_id");
                int customerId = rs.getInt("customer_id");
                Timestamp reservationDatetime = rs.getTimestamp("reservation_datetime");
                int partySize = rs.getInt("party_size");
                String status = rs.getString("status");

                // Create a Reservation object
                Reservation reservation = new Reservation(reservationId, customerId, reservationDatetime, partySize, status);
                reservations.add(reservation);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return reservations;
}

 public List<Customers> findAllCustomers() {
    List<Customers> customers = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM customers";
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String customerName = rs.getString("name");

                Customers customer = new Customers(customerId, customerName, null, null, null);
                customers.add(customer);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return customers;
}

public List<Reservation> getAllReservations() {
    List<Reservation> reservations = new ArrayList<>();

    String jdbcUrl = "jdbc:mysql://localhost:3306/restaurant_ordering_system_db";
    String dbUsername = "root";
    String dbPasswd = "gadiella";

    try (Connection con = DriverManager.getConnection(jdbcUrl, dbUsername, dbPasswd)) {
        String sql = "SELECT * FROM reservations";
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setCustomerId(rs.getInt("customer_id"));
                reservation.setReservationDatetime(rs.getTimestamp("reservation_datetime"));
                reservation.setPartySize(rs.getInt("party_size"));
                reservation.setStatus(rs.getString("status"));

                reservations.add(reservation);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return reservations;
}




    
}
