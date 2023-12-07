package com.example.backend.Admin.repository;

import com.example.backend.Admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    @Override
    boolean existsById(String s);
}
