package com.example.firstspringapp.controller;

import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dao.SupportDao;
import com.example.firstspringapp.dto.NoteDto;
import com.example.firstspringapp.dto.SupportDto;
import com.example.firstspringapp.service.NoteService;
import com.example.firstspringapp.service.SupportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/profile")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private SupportService supportService;

    @ApiOperation("Creating new note for customer")
    @PostMapping("/{id}/create")
    public NoteDto addNewNote(@PathVariable Long id, @RequestBody NoteDao note) {
        return noteService.addNewNote(id, note);
    }

    @ApiOperation("Creating support order")
    @PostMapping("/profile/{id}/support")
    public SupportDto postNewOrder(@PathVariable Long id, @RequestBody SupportDao supportDao) {
        return supportService.postNewOrder(id, supportDao);
    }

    @ApiOperation("Getting note by id")
    @GetMapping("/{id}/note")
    public NoteDto getCurrentNote(@PathVariable Long id, @RequestParam Long noteId) {
        return noteService.getCurrentNote(id, noteId);
    }

    @ApiOperation("Updating note by id")
    @PutMapping("/{id}/update")
    public NoteDto updateCurrentNote(@PathVariable Long id, @RequestParam Long noteId, @RequestBody NoteDto noteDto) {
        return noteService.updateCurrentNote(id, noteId, noteDto);
    }

    @ApiOperation("Get all notes from all customers")
    @GetMapping("/{id}/all")
    public List<NoteDto> showCustomerNotes(@PathVariable Long id) {
        return noteService.showAllCustomerNotes(id);
    }

}
