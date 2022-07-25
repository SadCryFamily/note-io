package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dto.NoteDto;

import java.util.List;

public interface NoteService {

    NoteDto addNewNote(Long id, NoteDao newNote);

    List<NoteDto> showAllCustomerNotes(Long id);

    NoteDto getCurrentNote(Long id, Long noteId);

    NoteDto updateCurrentNote(Long id, Long noteId, NoteDto noteDto);

}
