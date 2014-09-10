package com.realdolmen.course;

import com.realdolmen.course.domain.Address;
import com.realdolmen.course.domain.Passenger;
import org.hibernate.PropertyValueException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.util.Date;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
public class PassengerPersistenceTest extends DataSetPersistenceTest{

    @Rule
    public ExpectedException expector = ExpectedException.none();

    @Test(expected = PersistenceException.class)
    public void testPassengerPersisted() throws Exception {
        Passenger passenger = new Passenger("0000000001", "John", "Doe");
        entityManager().persist(passenger);
        entityManager().flush();
        Assert.assertNotNull(passenger.getId());
    }

    @Test
    public void testPassengerPersistedExercice4() throws Exception {
        Passenger passenger = new Passenger("00000000002", "Jane", "Doe", new Date(), Passenger.PassengerType.OCCASIONAL);
        entityManager().persist(passenger);
        Assert.assertNotNull(passenger.getId());
    }

    @Test
    public void testSsnNotUpdatable() throws Exception {
        Passenger passenger = new Passenger("00000000003", "Kevin", "De Man", new Date(), Passenger.PassengerType.OCCASIONAL);
        entityManager().persist(passenger);
        passenger.setSsn("00000000004");
        entityManager().merge(passenger);
        Passenger persistedPassenger = entityManager().find(Passenger.class, passenger.getId());
        Assert.assertNotEquals("00000000003", persistedPassenger.getSsn());
    }

    @Test
    public void testUpdateTimeStamp() throws Exception {
        Passenger passenger = new Passenger("00000000004", "Employee", "A", new Date(), Passenger.PassengerType.OCCASIONAL);
        entityManager().persist(passenger);
        Date insertTime = passenger.getLastUpdated();
        passenger.setLastName("XX");
        // entityManager().merge(passenger);
        Date updateTime = passenger.getLastUpdated();
        Assert.assertTrue( updateTime.getTime() >= insertTime.getTime() );
    }

    @Test
    public void testRetrieveById() throws Exception {
        Passenger passenger = new Passenger("00000000005", "Employee", "A", new Date(), Passenger.PassengerType.OCCASIONAL);
        entityManager().persist(passenger);
        long id = passenger.getId();
        entityManager().flush();
        entityManager().clear();
        Passenger managed = entityManager().find(Passenger.class, id);
        Assert.assertEquals("00000000005", managed.getSsn());
    }

    @Test
    public void testDelete() throws Exception {
        Passenger passenger = new Passenger("00000000006", "Employee", "A", new Date(), Passenger.PassengerType.OCCASIONAL);
        entityManager().persist(passenger);
        long id = passenger.getId();
        entityManager().flush();
        entityManager().remove(passenger);
        Assert.assertEquals(null, entityManager().find(Passenger.class, id));
    }

    @Test
    public void testAddPreferences() throws Exception {
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        passenger.addPreference("Not near wing");
        passenger.addPreference("Preferable seat at tail of plane");
        entityManager().persist(passenger);
        entityManager().flush();
        entityManager().clear();
        passenger = entityManager().find(Passenger.class, 1L);
        Assert.assertEquals(2, passenger.getPreferences().size() );
    }

    @Test
    public void testAddAddress() throws Exception {
        Passenger passenger = entityManager().find(Passenger.class, 1L);
        Address address = new Address("Zondernaamstraat", null, "Gent", "9000", "België" );
        passenger.setAddress( address );
        entityManager().persist(address);
        entityManager().flush();
        entityManager().clear();
        passenger = entityManager().find(Passenger.class, 1L);
        Assert.assertEquals(address.getStreet1(), passenger.getAddress().getStreet1());
    }
}
