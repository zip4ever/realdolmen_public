package com.realdolmen.course.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KDAAU95 on 9/09/2014.
 */
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;

    private BigDecimal price;
    private Date dateOfDeparture;
    private String destination;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne(cascade = CascadeType.ALL)
    private Flight flight;

    public Ticket() {
    }

    public Ticket(BigDecimal price, Date dateOfDeparture, String destination) {
        this.price = price;
        this.dateOfDeparture = dateOfDeparture;
        this.destination = destination;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(Date dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
