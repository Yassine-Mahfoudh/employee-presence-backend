package com.example.myapp.persistence.repository;

import com.example.myapp.persistence.model.RRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RRuleRepository extends JpaRepository<RRule,Long> {
}
