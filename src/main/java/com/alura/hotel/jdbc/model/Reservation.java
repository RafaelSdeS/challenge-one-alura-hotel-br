package com.alura.hotel.jdbc.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Reservation {
    private Integer id;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal value;
    private String paymentMethod;

    public Reservation(Date checkInDate, Date checkOutDate, BigDecimal value, String paymentMethod) {
        super();
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public Reservation(Integer id, Date checkInDate, Date checkOutDate, BigDecimal value, String paymentMethod) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.value = value;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
