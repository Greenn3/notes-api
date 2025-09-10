package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NoteCreateRequest(
        @NotBlank String title,
        String content,
        @NotNull Long authorId
) {
}
