package com.example.notes.dto;

import java.time.LocalDateTime;

public record NoteResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        Long authorId,
        String authorName
) {}