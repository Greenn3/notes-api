package com.example.notes.service;

import com.example.notes.Mapper;
import com.example.notes.dto.NoteCreateRequest;
import com.example.notes.entity.Author;
import com.example.notes.entity.Note;
import com.example.notes.exception.NotFoundException;
import com.example.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteService {
    private final NoteRepository notes;
    private final AuthorService authors;

    public NoteService(NoteRepository notes, AuthorService authors) {
        this.notes = notes;
        this.authors = authors;
    }

    public Note create(NoteCreateRequest req) {
        Author author = authors.getOrThrow(req.authorId());
        return notes.save(Mapper.toEntity(req, author));

    }

    @Transactional(readOnly = true)
    public Note getOrThrow(Long id) {
        return notes.findById(id).orElseThrow(() -> new NotFoundException("Note %d not found".formatted(id)));
    }

    @Transactional(readOnly = true)
    public List<Note> list() {
        return notes.findAll();
    }

    public void delete(Long id) {
        Note n = getOrThrow(id);
        notes.delete(n);
    }
}
