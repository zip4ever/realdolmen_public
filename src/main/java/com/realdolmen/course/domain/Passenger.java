package com.realdolmen.course.domain;

import org.hibernate.type.EnumType;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
@Entity
public class Passenger {

    private static Logger logger = LoggerFactory.getLogger(Passenger.class);

    public static enum PassengerType {
        OCCASIONAL, REGULAR;
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, updatable = false)
    private String ssn;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    private int frequentFlyerMiles;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private int age;

    @Enumerated(javax.persistence.EnumType.STRING)
    @Column(nullable = false)
    private PassengerType passengerType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFlight;

    private Date lastUpdated;

    @ElementCollection
    private List<String> preferences;

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets = new ArrayList<Ticket>();

    @OneToOne
    private Address address;

    public Passenger() {
    }

    public Passenger(String ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Passenger(String ssn, String firstName, String lastName, Date dateOfBirth, PassengerType passengerType) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passengerType = passengerType;
    }

    public Passenger(String ssn, String firstName, String lastName, Date dateOfBirth, PassengerType passengerType, Address address) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passengerType = passengerType;
        this.address = address;
    }

    public Passenger(String ssn, String firstName, String lastName, int frequentFlyerMiles) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFrequentFlyerMiles() {
        return frequentFlyerMiles;
    }

    public void setFrequentFlyerMiles(int frequentFlyerMiles) {
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Transient
    public int getAge() {
        Date now = new Date();
        DateTime birthday = new DateTime(dateOfBirth);
        DateTime current = new DateTime(now);
        return Years.yearsBetween(birthday, current).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public Date getLastFlight() {
        return lastFlight;
    }

    public void setLastFlight(Date lastFlight) {
        this.lastFlight = lastFlight;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void addPreference(String preference) {
        preferences.add(preference);
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void alterLastUpdated() {
        logger.info("Saving passenger ... ");
        lastUpdated = new Date();
        logger.info("Date set to : " + lastUpdated);
    }
}
