package com.realdolmen.course.domain;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * Created by KDAAU95 on 10/09/2014.
 */
@Entity
public class DomesticFlight extends Flight {

    private String airLineCompany;

    @ElementCollection
    @Column(name="`references`")
    private List<String> references;

    public DomesticFlight() {
    }

    public DomesticFlight(String number, Date dateOfDeparture, Date dateOfArrival, String airLineCompany, List<String> references) {
        super(number, dateOfDeparture, dateOfArrival);
        this.airLineCompany = airLineCompany;
        this.references = references;
    }

    public String getAirLineCompany() {
        return airLineCompany;
    }

    public void setAirLineCompany(String airLineCompany) {
        this.airLineCompany = airLineCompany;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }
}
