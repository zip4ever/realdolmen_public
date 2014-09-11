package com.realdolmen.course.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by KDAAU95 on 10/09/2014.
 */
@Entity
public class InternationalFlight extends Flight {

    private boolean blackListed;

    private String regulations;

    public InternationalFlight() {
    }

    public InternationalFlight(String number, Date dateOfDeparture, Date dateOfArrival, boolean blackListed, String regulations) {
        super(number, dateOfDeparture, dateOfArrival);
        this.blackListed = blackListed;
        this.regulations = regulations;
    }

    public boolean isBlackListed() {
        return blackListed;
    }

    public void setBlackListed(boolean blackListed) {
        this.blackListed = blackListed;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }
}
