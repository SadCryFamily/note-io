package com.example.firstspringapp.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "note")
public class NoteDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    private String body;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerDao customerDao;
}
