package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dto.NoteDto;
import com.example.firstspringapp.repository.CustomerRepository;
import com.example.firstspringapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Transactional
    @Override
    public NoteDto addNewNote(Long id, NoteDao newNote) {
        CustomerDao existedCustomer = customerRepository.findById(id).orElseThrow();

        existedCustomer.createNote(newNote);

        return new NoteDto(newNote);
    }

    @Override
    public List<NoteDto> showAllCustomerNotes(Long id) {
        var test = Optional.ofNullable(noteRepository.getCustomerNotes(id))
                .orElseThrow(() -> new RuntimeException("Ops! You dont have any notes."));

        return test.parallelStream().map(dao -> new NoteDto(dao))
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto getCurrentNote(Long id, Long noteId) {
        var customerIsExist = Optional.ofNullable(customerRepository.findById(id))
                .orElseThrow(() -> new RuntimeException("Customer with ID: " + id + " not created!"));

        var currentNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note with ID: " + noteId + " not created!"));

        return NoteDto.builder()
                .title(currentNote.getTitle())
                .body(currentNote.getBody())
                .build();
    }
}
