package com.example.notes;

import com.example.notes.dto.*;
import com.example.notes.entity.*;

public final class Mapper {
    private Mapper() {}

    // === Entity -> Response DTO ===
    public static AuthorCreateResponse toResponse(Author a) {
        return new AuthorCreateResponse(a.getId(), a.getName());
    }

    public static NoteResponse toResponse(Note n) {
        return new NoteResponse(
                n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(),
                n.getAuthor().getId(), n.getAuthor().getName()
        );
    }

    // === Request DTO -> Entity ===
    public static Author toEntity(AuthorCreateRequest req) {
        Author a = new Author();
        a.setName(req.name().trim());
        return a;
    }

    public static Note toEntity(NoteRequest req, Author author) {
        return new Note(req.title().trim(), req.content(), author);
    }


    public static void update(Note note, NoteRequest req, Author author) {
        note.setTitle(req.title().trim());
        note.setContent(req.content());
        note.setAuthor(author);
    }
}
