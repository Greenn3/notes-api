package com.example.notes.controller;

import com.example.notes.Mapper;
import com.example.notes.dto.AuthorCreateRequest;
import com.example.notes.dto.AuthorResponse;
import com.example.notes.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Authors", description = "Operations on authors")
@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Create an author")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Validation error",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse create(@Valid @RequestBody AuthorCreateRequest req) {
        return Mapper.toResponse(authorService.create(req));
    }

    @Operation(summary = "List authors")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<AuthorResponse> all() {
        return authorService.list().stream().map(Mapper::toResponse).toList();
    }

    @Operation(summary = "Get author by ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/{id}")
    public AuthorResponse byId(@PathVariable Long id) {
        return Mapper.toResponse(authorService.getOrThrow(id));
    }
}
