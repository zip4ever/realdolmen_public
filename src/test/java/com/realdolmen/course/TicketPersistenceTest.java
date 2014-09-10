package com.realdolmen.course;

import com.realdolmen.course.domain.Ticket;
import junit.framework.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by KDAAU95 on 9/09/2014.
 */
public class TicketPersistenceTest extends PersistenceTest {

    @Test
    public void testCreate() throws Exception {
        Ticket ticket = new Ticket(new BigDecimal("50.1"), new Date(), "Tel Aviv");
        entityManager().persist(ticket);
        Assert.assertNotNull(ticket.getId());
    }

    @Test
    public void testRetrieveById() throws Exception {
        Ticket ticket = new Ticket(new BigDecimal("50.1"), new Date(), "Tel Aviv");
        entityManager().persist(ticket);
        long id = ticket.getId();
        entityManager().flush();
        Ticket managed = entityManager().find(Ticket.class, id);
        Assert.assertEquals(ticket, managed);
    }

    @Test
    public void testUpdate() throws Exception {
        Ticket ticket = new Ticket(new BigDecimal("50.1"), new Date(), "Tel Aviv");
        entityManager().persist(ticket);
        entityManager().flush();
        long id = ticket.getId();
        ticket.setDestination("Bagdad");
        entityManager().flush();
        entityManager().clear();
        Ticket managed = entityManager().find(Ticket.class, id);
        Assert.assertEquals("Bagdad", managed.getDestination());
    }

    @Test
    public void testDelete() throws Exception {
        Ticket ticket = new Ticket(new BigDecimal("50.1"), new Date(), "Tel Aviv");
        entityManager().persist(ticket);
        long id = ticket.getId();
        entityManager().flush();
        entityManager().remove(ticket);
        org.junit.Assert.assertEquals(null, entityManager().find(Ticket.class, id));
    }

    @Test
    public void testRefresh() throws Exception {
        Ticket ticket = new Ticket(new BigDecimal("50.1"), new Date(), "Tel Aviv");
        entityManager().persist(ticket);
        entityManager().flush();
        ticket.setDestination("Brussels");
        entityManager().refresh(ticket);
        org.junit.Assert.assertEquals("Tel Aviv", ticket.getDestination());
    }


}
