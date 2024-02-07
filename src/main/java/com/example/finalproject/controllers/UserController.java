package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping("/getBalance/{id}")
    ResponseEntity getBalanceById(@PathVariable long id) {
        User user = userService.getBalance(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping("/takeMoney/{id}")
    ResponseEntity getTakeMoneyByBalance(@PathVariable long id, @RequestParam long money) {
        userService.takeMoney(id, money);
        logger.info("Снятие средств прошло успешно");
        return ResponseEntity.status(HttpStatus.OK).body("Успех (1)");
    }

    @GetMapping("/putMoney/{id}")
    ResponseEntity putMoneyByBalance(@PathVariable long id, @RequestParam Long money) {
        userService.putMoney(id, money);
        return ResponseEntity.status(HttpStatus.OK).body("Успех (1)");
    }

    @GetMapping("/listOperation/{id}")
    ResponseEntity getListOperations(
            @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOperationList(id));
    }

    @GetMapping("/test/{id}")
    ResponseEntity getTestUser(@PathVariable long id) {
        logger.error("Запускаем userService");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getTest(id));
    }

}
