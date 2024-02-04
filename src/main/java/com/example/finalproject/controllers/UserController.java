package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getBalance/{id}")
    ResponseEntity getBalanceById(@PathVariable Long id) {
        User user = userService.getBalance(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping("/takeMoney/{id}")
    ResponseEntity getTakeMoneyByBalance(@PathVariable Long id, @RequestParam Long money) {
        User user = userService.takeMoney(id, money);
        userService.takeMoney(id, money);
        return ResponseEntity.status(HttpStatus.OK).body("Успех (1)");
    }

    @GetMapping("/putMoney/{id}")
    ResponseEntity putMoneyByBalance(@PathVariable Long id, @RequestParam Long money) {
        userService.putMoney(id, money);
        return ResponseEntity.status(HttpStatus.OK).body("Успех (1)");
    }
}
