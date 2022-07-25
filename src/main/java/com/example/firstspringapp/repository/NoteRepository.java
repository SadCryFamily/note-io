package com.example.firstspringapp.repository;

import com.example.firstspringapp.dao.NoteDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<NoteDao, Long> {

    @Query(value = "SELECT * FROM note WHERE customer_id in (:id);", nativeQuery = true)
    List<NoteDao> getCustomerNotes(@Param("id") Long id);

}
