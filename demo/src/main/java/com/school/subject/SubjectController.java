package com.school.subject;

import com.school.author.Author;
import com.school.author.AuthorRepository;
import com.school.book.Book;
import com.school.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    SubjectService subjectService;

    @GetMapping
    List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }


    @GetMapping("/{id}")
    Set<Book> getStudentAssignedToSubject(@PathVariable("id")Long id){
        return subjectService.getBooksByMemberId(id);
    }
    @PostMapping
    Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    Subject addStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ) {
        return subjectService.addBookToSubject(subjectId,studentId);
    }

    @PutMapping("/{id}")
    Subject editMember(@RequestBody Subject subject, @PathVariable("id") Long id){
        return subjectService.editMember(id, subject);
    }

    @DeleteMapping("/members/{id}")
    public Subject deleteMember(@PathVariable("id") Long id){
        Subject subject = subjectService.deleteSubject(id);
        return subject;
    }

    @DeleteMapping("/books/{bookId}/members/{memberId}")
    public Subject deleteMemberFromBook(@PathVariable("bookId") Long bookId, @PathVariable("memberId") Long memberId){

        return subjectService.deleteBookFromMember(memberId,bookId);
    }
    @PutMapping("/{subjectId}/teacher/{teacherId}")
    Subject assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Author author = authorRepository.findById(teacherId).get();
        subject.setTeacher(author);
        return subjectRepository.save(subject);
    }
}
