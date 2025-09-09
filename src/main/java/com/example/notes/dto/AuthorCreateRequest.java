package com.example.notes.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorCreateRequest(@NotBlank String name) {}
