package com.example.finalproject.repository;

import com.example.finalproject.entity.BaseOfOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<BaseOfOperation, Long> {
}
