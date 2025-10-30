/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Gisabo
 */
public class Payments {
     private int paymentId;
    private int orderId;
    private Timestamp paymentDate;
    private BigDecimal amountPaid;
    private String paymentMethod;
    private String paymentStatus;

    public Payments() {
    }

    public Payments(int paymentId, int orderId, Timestamp paymentDate,  BigDecimal amountPaid, String paymentMethod, String paymentStatus) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
    
}
