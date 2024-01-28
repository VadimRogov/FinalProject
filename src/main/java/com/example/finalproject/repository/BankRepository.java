package com.example.finalproject.repository;

import com.example.finalproject.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends CrudRepository<User, Long> {
    User getById(long id);
}
