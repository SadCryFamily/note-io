package com.example.firstspringapp.dto;

import com.example.firstspringapp.dao.NoteDao;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoteDto {

    private String title;

    private String body;

    public NoteDto(NoteDao noteDao) {
        this.setTitle(noteDao.getTitle());
        this.setBody(noteDao.getBody());
    }
}
