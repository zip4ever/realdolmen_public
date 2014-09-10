package com.realdolmen.course;

import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Ticket;
import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by KDAAU95 on 10/09/2014.
 */
public class FlightPersistenceTest extends PersistenceTest {

    @Test
    public void testFlightCanBePersisted() throws Exception {
        Flight flight = new Flight("MH17", new Date(), null);
        entityManager().persist(flight);
        Assert.assertNotNull(flight.getId());
    }

    @Test
    public void testFlightWithTicketsCanBePersisted() throws Exception {
        Flight flight = new Flight("MH17", new Date(), null);
        List<Ticket> tickets = new ArrayList<Ticket>();
        entityManager().persist(flight);
        for(int i=0; i<100; i++) {
            Ticket ticket = new Ticket(new BigDecimal("50.1"), new Date(), "Tel Aviv " + i);
            ticket.setFlight( flight );
            tickets.add( ticket );
        }
        flight.setTickets(tickets);
        entityManager().persist(flight);
        long id = flight.getId();
        entityManager().flush();
        entityManager().clear();
        flight = entityManager().find(Flight.class, id );
        Assert.assertNotNull(flight.getTickets());
    }
}
