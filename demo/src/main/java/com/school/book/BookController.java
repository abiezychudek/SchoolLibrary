package com.school.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;

    @GetMapping
    List<Book> getStudents() {
        return bookService.getBooks();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    @PostMapping
    Book createStudent(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/book/{id}")
    public Book editBook(@RequestBody Book book, @PathVariable("id") Long id){
        return bookService.editBook(id,book);
    }
    @DeleteMapping("/book/{id}")
    public Book deleteBook(@PathVariable("id")  Long id){
        Book book=bookService.getBook(id);
        bookService.deleteBook(id);
        return book;
    }
}