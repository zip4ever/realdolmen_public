package com.realdolmen.course.main;

import com.realdolmen.course.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
public class Runner {

    public static void main( String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("PassengerPersistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(new Book("1984", "George Orwell"));
            transaction.commit();
        } finally {
           if(entityManager !=null ) {
               entityManager.close();
           }
            if(entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
