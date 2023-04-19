package com.school.subject;

import com.school.author.AuthorRepository;
import com.school.book.Book;
import com.school.book.BookException;
import com.school.book.BookRepository;
import com.school.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookService bookService;
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addMember(Subject subject) {
        subjectRepository.save(subject);
        return subject;
    }
    public Set<Book> getBooksByMemberId(Long memberId) {
        Subject subject = subjectRepository.findById(memberId).orElseThrow(() -> new BookException(memberId));
        return subject.getEnrolledBooks();
    }
    public List<Subject> getSubjects() {
        return StreamSupport
                .stream(subjectRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Subject getSubject(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new BookException(id));
    }

    public Subject deleteSubject(Long id) {
        Subject subject = getSubject(id);
        subjectRepository.deleteById(id);
        return subject;
    }

    public Subject addBookToSubject(Long memId, Long bookId){
        Subject subject = getSubject(memId);
        Book book= bookService.getBook(bookId);
        subject.enrolledBooks.add(book);
        subjectRepository.save(subject);
        return subject;
    }

    public Subject deleteBookFromMember(Long memId, Long bookId){
        Subject subject = getSubject(memId);
        Book book= bookService.getBook(bookId);
        subject.enrolledBooks.remove(book);
        subjectRepository.save(subject);
        return subject;
    }
    @Transactional
    public Subject editMember(Long id, Subject subject) {
        Subject subject1 = getSubject(id);
        subject1.setAge(subject.getAge());
        subject1.setName(subject.getName());
        subject1.setSurname(subject.getSurname());
        return subject1;
    }
}
