package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dto.NoteDto;
import com.example.firstspringapp.service.NoteService;
import com.example.firstspringapp.service.SupportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @MockBean
    private NoteService noteService;

    @MockBean
    private SupportService supportService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewNote() throws Exception {

        var tempNote = NoteDao.builder().Id(1L).title("title").body("body").build();
        var tempNoteDto = new NoteDto(tempNote);

        Mockito.when(noteService.addNewNote(1L, tempNote)).thenReturn(tempNoteDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/profile/{id}/create", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(tempNote)))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void getCurrentNoteTest() throws Exception {

        var note = NoteDto.builder().title("title").body("body").build();

        Mockito.when(noteService.getCurrentNote(1L, 1L)).thenReturn(note);

        this.mockMvc.perform(get("/profile/{id}/note", 1).param("noteId", "1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.title", Matchers.is("title")))
                .andExpect(jsonPath("$.body", Matchers.is("body")));

    }

    @Test
    public void updateCurrentNote() throws Exception {

        var noteDto = NoteDto.builder().title("title").body("body").build();

        Mockito.when(noteService.updateCurrentNote(1L, 1L, noteDto)).thenReturn(noteDto);

        this.mockMvc.perform(put("/profile/{id}/update", 1).param("noteId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(noteDto)))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void showCustomerNotes() throws Exception {

        var noteDto = NoteDto.builder().title("title").body("body").build();
        var list = List.of(noteDto);

        Mockito.when(noteService.showAllCustomerNotes(1L)).thenReturn(list);

        this.mockMvc.perform(get("/profile/{id}/all", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].title", Matchers.is("title")))
                .andExpect(jsonPath("$[0].body", Matchers.is("body")));

    }
}