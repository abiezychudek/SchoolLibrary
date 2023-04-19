package com.school.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping
    List<Author> getTeachers() {
        return authorRepository.findAll();
    }

    @PostMapping
    Author createTeacher(@RequestBody Author author) {
        return authorRepository.save(author);
    }
}
