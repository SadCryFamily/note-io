package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dto.NoteDto;

public interface NoteService {

    NoteDto addNewNote(Long id, NoteDao newNote);

}
