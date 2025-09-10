package com.example.notes.controller;

import com.example.notes.Mapper;
import com.example.notes.dto.NoteCreateRequest;
import com.example.notes.dto.NoteResponse;
import com.example.notes.service.NoteService;
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

@Tag(name = "Notes", description = "Operations on notes")
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService svc) {
        this.noteService = svc;
    }

    @Operation(summary = "Create a note")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Validation/business error",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "404", description = "Author not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse create(@Valid @RequestBody NoteCreateRequest req) {
        return Mapper.toResponse(noteService.create(req));
    }

    @Operation(summary = "List notes")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public List<NoteResponse> all() {
        return noteService.list().stream().map(Mapper::toResponse).toList();
    }

    @Operation(summary = "Get note by ID")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @GetMapping("/{id}")
    public NoteResponse byId(@PathVariable Long id) {
        return Mapper.toResponse(noteService.getOrThrow(id));
    }

    @Operation(summary = "Delete note")
    @ApiResponse(responseCode = "204", description = "Deleted (no content)")
    @ApiResponse(responseCode = "404", description = "Not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
