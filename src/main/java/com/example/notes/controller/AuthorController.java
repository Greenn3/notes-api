package com.example.notes.controller;

import com.example.notes.Mapper;
import com.example.notes.dto.AuthorCreateRequest;
import com.example.notes.dto.AuthorCreateResponse;
import com.example.notes.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService){ this.authorService = authorService; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorCreateResponse create(@Valid @RequestBody AuthorCreateRequest req) {
        return Mapper.toResponse(authorService.create(req));
    }

    @GetMapping
    public List<AuthorCreateResponse> all() {
        return authorService.list().stream().map(Mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public AuthorCreateResponse byId(@PathVariable Long id) {
        return Mapper.toResponse(authorService.getOrThrow(id));
    }
}
