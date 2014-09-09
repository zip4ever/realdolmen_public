package com.realdolmen.course.domain;

import javax.persistence.*;

/**
 * Created by KDAAU95 on 8/09/2014.
 */
@Entity
public class Book {

    public static enum Genre {
        FANTASY, THRILLER, ROMAN, DETECTIVE;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String author;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    /**
     * Used by JPA
     */
    protected Book() {
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
