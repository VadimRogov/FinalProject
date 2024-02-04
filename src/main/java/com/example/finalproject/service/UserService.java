package com.example.finalproject.service;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getBalance(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public User takeMoney(long id, long money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        user.setBalans(user.getBalans() - money);
        return userRepository.save(user);
    }
}
