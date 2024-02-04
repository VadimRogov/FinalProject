package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getBalance/{id}")
    User getBalanceById(@PathVariable Long id) {
        return userService.getBalance(id);
    }


    @GetMapping("/takeMoney/{id}/{money}")
    User getTakeMoneyByBalance(@PathVariable Long id, @RequestParam Long money) {
        return userService.takeMoney(id, money);
    }
}
