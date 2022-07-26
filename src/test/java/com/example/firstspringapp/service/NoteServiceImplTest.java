package com.example.firstspringapp.service;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dao.NoteDao;
import com.example.firstspringapp.dto.NoteDto;
import com.example.firstspringapp.repository.CustomerRepository;
import com.example.firstspringapp.repository.NoteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@SpringBootTest
@RunWith(SpringRunner.class)
public class NoteServiceImplTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;

    @Test
    public void showAllCustomerNotesTest() {

        var newCustomer = CustomerDao.builder().id(1L).fullName("test").build();
        var listOfNotes = List.of(NoteDao.builder()
                .Id(1L)
                .title("title")
                .body("body").build());

        Mockito.when(noteRepository.getCustomerNotes(1L)).thenReturn(listOfNotes);

        var dtoList = listOfNotes.stream()
                .map(dao -> new NoteDto(dao))
                .collect(Collectors.toList());

        var result = noteService.showAllCustomerNotes(1L);

        Assert.assertEquals(result.get(0).getTitle(), dtoList.get(0).getTitle());
        Assert.assertEquals(result.get(0).getBody(), dtoList.get(0).getBody());
    }

    @Test
    public void getCurrentNoteTest() {

        var newCustomer = CustomerDao.builder().id(1L).fullName("test").build();

        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(newCustomer));

        var noteDao = NoteDao.builder().Id(1L).title("title").body("body").build();

        Mockito.when(noteRepository.findById(1L)).thenReturn(Optional.ofNullable(noteDao));

        var noteDto = new NoteDto(noteDao);
        var result = noteService.getCurrentNote(1L, 1L);

        Assert.assertEquals(result.getTitle(), noteDto.getTitle());
        Assert.assertEquals(result.getBody(), noteDto.getBody());

    }

    @Test
    public void updateCurrentNoteTest() {

        var newCustomer = CustomerDao.builder().id(1L).fullName("test").build();

        Mockito.when(customerRepository.findById(newCustomer.getId()))
                .thenReturn(Optional.ofNullable(newCustomer));

        var updatableNote = NoteDao.builder().Id(1L).title("title").body("body").build();

        var noteDto = NoteDto.builder()
                .title("test")
                .body("test")
                .build();

        Mockito.when(noteRepository.getById(1L)).thenReturn(updatableNote);

        updatableNote.setTitle(noteDto.getTitle());
        updatableNote.setBody(noteDto.getBody());

        Mockito.when(noteRepository.save(updatableNote)).thenReturn(updatableNote);

        var result = noteService.updateCurrentNote(1L, 1L, noteDto);

        Assert.assertEquals(result.getTitle(), updatableNote.getTitle());
        Assert.assertEquals(result.getBody(), updatableNote.getBody());
    }
}