package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.BankRepository;
import com.example.finalproject.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BankController {

    @Autowired
    private BankRepository bankRepository;


    @RequestMapping("/balans/{id}")
    public String currentBalance(@RequestBody User user) {
        return "Текущий баланс: " + user.getBalans();

    }
}
