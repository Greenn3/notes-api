package com.example.notes;

import com.example.notes.dto.*;
import com.example.notes.entity.*;

public final class Mapper {
    private Mapper() {
    }

    public static AuthorResponse toResponse(Author a) {
        return new AuthorResponse(a.getId(), a.getName());
    }

    public static NoteResponse toResponse(Note n) {
        return new NoteResponse(
                n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(),
                n.getAuthor().getId(), n.getAuthor().getName()
        );
    }

    public static Author toEntity(AuthorCreateRequest req) {
        Author a = new Author();
        a.setName(req.name().trim());
        return a;
    }

    public static Note toEntity(NoteCreateRequest req, Author author) {
        return new Note(req.title().trim(), req.content(), author);
    }


}
