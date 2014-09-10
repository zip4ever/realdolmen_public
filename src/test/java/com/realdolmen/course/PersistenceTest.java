package com.realdolmen.course;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
public abstract class PersistenceTest {


    private final static Logger logger = LoggerFactory.getLogger(DataSetPersistenceTest.class);


    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeClass
    public static void initializeEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PassengerPersistenceUnit");
    }

    @Before
    public void initialize() {
        logger.info("Start initializing ...");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterClass
    public static void destroyEntityManagerFactory() {
        logger.info("Start destroy factory");
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @After
    public void destroy() {
        if( transaction != null && !transaction.getRollbackOnly() ) {
            logger.info("Commit transaction...");
            transaction.commit();
            // transaction.rollback();    would fix issue of retestability but cannot be checked on db
        }

        if( entityManager != null) {
            logger.info("Close entitymanager...");
            entityManager.close();
        }
    }

    protected EntityManager entityManager() {
        return this.entityManager;
    }
}
