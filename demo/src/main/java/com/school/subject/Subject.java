package com.school.subject;

import com.school.author.Author;
import com.school.book.Book;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    private String surname;
    private int age;

    @ManyToMany
    @JoinTable(
            name = "book_enrolled",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    Set<Book> enrolledBooks = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Author author;

    public Subject(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Subject(Long id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public Set<Book> getEnrolledBooks() {
        return enrolledBooks;
    }

    public Author getTeacher() {
        return author;
    }

    public void setTeacher(Author author) {
        this.author = author;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
