package com.example.finalproject.service;

import com.example.finalproject.entity.BaseOfOperation;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OperationRepository operationRepository;

    public UserService(UserRepository userRepository, OperationRepository operationRepository) {
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
    }


    public User getBalance(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public User takeMoney(long id, long money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if(user.getBalance() >= money) {
            user.setBalance(user.getBalance() - money);
        }else {
            throw new IllegalArgumentException();
        }
        BaseOfOperation baseOfOperations = operationRepository.getReferenceById(user.getId());
        baseOfOperations.setType_operation(1);
        baseOfOperations.setAmount(baseOfOperations.getAmount() + 1);
        operationRepository.save(baseOfOperations);
        return userRepository.save(user);
    }

    public User putMoney(long id, long money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        user.setBalance(user.getBalance() + money);
        return userRepository.save(user);
    }
}
