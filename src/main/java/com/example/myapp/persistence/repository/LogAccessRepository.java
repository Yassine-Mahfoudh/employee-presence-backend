package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.LogAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAccessRepository extends JpaRepository<LogAccess,Long> {
}
