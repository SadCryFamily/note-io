package com.example.firstspringapp.dto;

import com.example.firstspringapp.dao.CustomerDao;
import com.example.firstspringapp.dao.NoteDao;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoteDto {

    @Autowired
    private NoteDao noteDao;

    private Long Id;

    private String title;

    private String body;

    public NoteDto(NoteDao noteDao) {
        this.setTitle(noteDao.getTitle());
        this.setBody(noteDao.getBody());
    }
}
