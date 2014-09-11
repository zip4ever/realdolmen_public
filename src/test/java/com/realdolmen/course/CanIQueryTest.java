package com.realdolmen.course;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.Ticket;
import junit.framework.Assert;
import org.junit.Test;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by KDAAU95 on 11/09/2014.
 */
public class CanIQueryTest extends PersistenceTest {

    @Test
    public void testAboveAverage() throws Exception {
        BigDecimal min = new BigDecimal(0);
        BigDecimal max = new BigDecimal(1000);
        for( int i=0; i<10; i++) {
            BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
            randomBigDecimal = randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
            Ticket ticket = new Ticket(randomBigDecimal, new Date(), "Destination " + i);
            Passenger passenger = new Passenger("" + i, "Passenger" , "" + i, new Date(), Passenger.PassengerType.OCCASIONAL);
            passenger.addTicket(ticket);
            entityManager().persist(passenger);
        }
        entityManager().flush();
        Query query = entityManager().createQuery("Select p from Passenger p join Ticket t where t.price > (select avg(t2.price) from Ticket t2)");
        Assert.assertNotNull(query.getResultList());
    }
}
