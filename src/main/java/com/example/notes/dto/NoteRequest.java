package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NoteRequest(
        @NotBlank String title,
        String content,
        @NotNull Long authorId
) {}
