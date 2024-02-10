package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    OperationRepository operationRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void testGetBalance() {
        logger.info("Создаём класс пользователя");
        User user1 = new User();
        logger.info("Инициализируем баланс");
        user1.setId(1L);
        user1.setBalance(BigDecimal.valueOf(1000));
        logger.info("Описываем поведение");
        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        logger.info("Вызываем метод userService получаем баланс");
        User user2 = userRepository.findById(user1.getId()).orElseThrow();

        int result = user2.getBalance().compareTo(BigDecimal.valueOf(1000));
        assertEquals(result, 0);
    }
}
