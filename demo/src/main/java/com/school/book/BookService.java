package com.school.book;

import com.school.Exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookException(id));
    }

    public Book deleteBook(Long id) {
        Book book = getBook(id);
        bookRepository.deleteById(id);
        return book;
    }
    @Transactional
    public Book editBook(Long id, Book book) {
        Book book1 = getBook(id);
        book1.setAvailability(book.isAvailability());
        return book1;
    }
}
