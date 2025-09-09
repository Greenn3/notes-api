package com.example.notes.service;

import com.example.notes.Mapper;
import com.example.notes.dto.AuthorCreateRequest;
import com.example.notes.entity.Author;
import com.example.notes.exception.NotFoundException;
import com.example.notes.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorService {
    private final AuthorRepository authors;

    public AuthorService(AuthorRepository authors) { this.authors = authors; }

    public Author create(AuthorCreateRequest req) {
        return authors.save(Mapper.toEntity(req));
    }

    @Transactional(readOnly = true)
    public Author getOrThrow(Long id) {
        return authors.findById(id).orElseThrow(() -> new NotFoundException("Author %d not found".formatted(id)));
    }

    @Transactional(readOnly = true)
    public List<Author> list() { return authors.findAll(); }
}
