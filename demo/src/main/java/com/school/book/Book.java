package com.school.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.subject.Subject;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private boolean availability;
    private Date date_of_publication;
    private Date date_of_borrowed;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledBooks")
    private Set<Subject> subjects = new HashSet<>();

    public Book(String title, boolean availability, Date date_of_publication, Date date_of_borrowed, String name) {
        this.title = title;
        this.availability = availability;
        this.date_of_publication = date_of_publication;
        this.date_of_borrowed = date_of_borrowed;
        this.name = name;
    }

    public Book(Long id, String title, boolean availability, Date date_of_publication, Date date_of_borrowed, String name) {
        this.id = id;
        this.title = title;
        this.availability = availability;
        this.date_of_publication = date_of_publication;
        this.date_of_borrowed = date_of_borrowed;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Date getDate_of_publication() {
        return date_of_publication;
    }

    public void setDate_of_publication(Date date_of_publication) {
        this.date_of_publication = date_of_publication;
    }

    public Date getDate_of_borrowed() {
        return date_of_borrowed;
    }

    public void setDate_of_borrowed(Date date_of_borrowed) {
        this.date_of_borrowed = date_of_borrowed;
    }
}
