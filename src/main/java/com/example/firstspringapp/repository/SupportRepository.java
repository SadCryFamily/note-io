package com.example.firstspringapp.repository;

import com.example.firstspringapp.dao.SupportDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<SupportDao, Long> {

    @Query(value = "SELECT * FROM support WHERE customer_id in (:id);" , nativeQuery = true)
    List<SupportDao> getAllOrderFor(@Param("id") Long id);
}
