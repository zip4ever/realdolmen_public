package com.realdolmen.course;

import com.realdolmen.course.domain.DomesticFlight;
import com.realdolmen.course.domain.InternationalFlight;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by KDAAU95 on 10/09/2014.
 */
public class FlightSubTypesPersistenceTest extends PersistenceTest{

    @Test
    public void testCanPersistDomestic() throws Exception {
        DomesticFlight domesticFlight = new DomesticFlight("ABC", new Date(), new Date(), "Bleh", null);
        entityManager().persist(domesticFlight);
        Assert.assertNotNull(domesticFlight.getId());
    }

    @Test
    public void testCanPersistInternational() throws Exception {
        InternationalFlight internationalFlight = new InternationalFlight("ABC", new Date(), new Date(), false, "Bleh");
        entityManager().persist(internationalFlight);
        Assert.assertNotNull(internationalFlight.getId());
    }
}
