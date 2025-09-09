package com.example.notes.controller;

import com.example.notes.Mapper;
import com.example.notes.dto.NoteRequest;
import com.example.notes.dto.NoteResponse;
import com.example.notes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    public NoteController(NoteService svc){ this.noteService = svc; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse create(@Valid @RequestBody NoteRequest req) {
        return Mapper.toResponse(noteService.create(req));
    }

    @GetMapping
    public List<NoteResponse> all() {
        return noteService.list().stream().map(Mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public NoteResponse byId(@PathVariable Long id) {
        return Mapper.toResponse(noteService.getOrThrow(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
