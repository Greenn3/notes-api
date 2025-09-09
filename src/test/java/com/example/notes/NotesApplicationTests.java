package com.example.notes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NotesApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    void createAuthorAndNote_flow() throws Exception {
        // Create author
        String authorJson = "{\"name\":\"Ada\"}";
        String authorRes = mvc.perform(post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn().getResponse().getContentAsString();

        // Extract author id (quick & dirty)
        long authorId = Long.parseLong(authorRes.replaceAll(".*\"id\":(\d+).*", "$1"));

        // Create note
        String noteJson = "{\"title\":\"Hello\",\"content\":\"World\",\"authorId\":"+authorId+"}";
        mvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(noteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.author.id").value(authorId));

        // List notes
        mvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Hello"));
    }
}
