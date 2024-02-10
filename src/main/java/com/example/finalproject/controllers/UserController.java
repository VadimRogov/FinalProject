package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@AllArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @GetMapping("/getBalance/{id}")
    ResponseEntity getBalanceById(@PathVariable long id) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getBalance(id));
    }
    @Transactional
    @GetMapping("/takeMoney/{id}")
    ResponseEntity getTakeMoneyByBalance(@PathVariable long id, @RequestParam BigDecimal money) {
        logger.info("Вызываем сервис, передаём аргументы");
        return ResponseEntity.status(HttpStatus.OK).body(userService.takeMoney(id, money));
    }

    @Transactional
    @GetMapping("/putMoney/{id}")
    ResponseEntity putMoneyByBalance(@PathVariable long id, @RequestParam BigDecimal money) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.putMoney(id, money));
    }

    @Transactional
    @GetMapping("/listOperation/{id}")
    ResponseEntity getListOperations(
            @PathVariable long id,
            @RequestParam(value = "beginDate", required = false) Date beginDate,
            @RequestParam(value = "endDate", required = false) Date endDate) {
        logger.info("Вызываем сервис, передаём параметры");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOperationList(id, beginDate, endDate));
    }
    @Transactional
    @GetMapping("/transfer/{sender_id}/{recipient_id}")
    ResponseEntity transferMoneyById(
            @RequestParam(value = "sender_id") long sender_id,
            @RequestParam(value = "recipient_id") long recipient_id,
            @RequestParam(value = "money") BigDecimal money) {
        logger.info("Вызываем сервис, передаём параметры");
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.transferMoney(sender_id, recipient_id, money));
    }
}

