package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dto.NoteDto;
import com.example.firstspringapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/{id}/create")
    public NoteDto addNewNote(@PathVariable Long id, @RequestBody NoteDao note) {
        return noteService.addNewNote(id, note);
    }

    @GetMapping("/{id}/note")
    public NoteDto getCurrentNote(@PathVariable Long id, @RequestParam Long noteId) {
        return noteService.getCurrentNote(id, noteId);
    }

    @GetMapping("/{id}/all")
    public List<NoteDto> showCustomerNotes(@PathVariable Long id) {
        return noteService.showAllCustomerNotes(id);
    }
}
