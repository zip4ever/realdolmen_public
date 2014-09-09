package com.realdolmen.course;

import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
public abstract class PersistenceTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeClass
    public static void initializeEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PassengerPersistenceUnit");
    }

    @Before
    public void initialize() {
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterClass
    public static void destroyEntityManagerFactory() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @After
    public void destroy() {
        if( transaction != null && !transaction.getRollbackOnly() ) {
                transaction.commit();

        }

        if( entityManager != null) {
            entityManager.close();
        }
    }

    protected EntityManager entityManager() {
        return this.entityManager;
    }
}
