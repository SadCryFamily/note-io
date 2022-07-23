package com.example.firstspringapp.repository;

import com.example.firstspringapp.dao.NoteDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<NoteDao, Long> {

}
