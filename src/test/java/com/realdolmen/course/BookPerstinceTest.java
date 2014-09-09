package com.realdolmen.course;

import com.realdolmen.course.domain.Book;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
public class BookPerstinceTest extends PersistenceTest {

    @Test
    public void testBookCanBePersisted() throws Exception {
        Book book = new Book("Animal farm", "George Orwell");
        entityManager().persist(book);
        Assert.assertNotEquals(book.getId(), null);
    }
}
