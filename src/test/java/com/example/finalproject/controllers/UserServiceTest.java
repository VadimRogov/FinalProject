package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    OperationRepository operationRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void testGetBalance() {
        logger.info("Создаём класс пользователя");
        User user = Mockito.mock(User.class);
        logger.info("Инициализируем баланс");
        user.setBalance(BigDecimal.valueOf(1000));
        logger.info("Описываем");
        Mockito.when(userService.getBalance(user.getId())).thenReturn(String.valueOf(user.getBalance()));


    }
}
