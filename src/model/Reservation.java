/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Gisabo
 */
public class Reservation {
    
    private int reservationId;
    private int customerId;
    private Timestamp reservationDatetime;
    private int partySize;
    private String status;

    public Reservation() {
    }

    public Reservation(int reservationId, int customerId, Timestamp reservationDatetime, int partySize, String status) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.reservationDatetime = reservationDatetime;
        this.partySize = partySize;
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Timestamp getReservationDatetime() {
        return reservationDatetime;
    }

    public void setReservationDatetime(Timestamp reservationDatetime) {
        this.reservationDatetime = reservationDatetime;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
