package com.example.firstspringapp.repository;

import com.example.firstspringapp.dao.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDao, Long> {
}
