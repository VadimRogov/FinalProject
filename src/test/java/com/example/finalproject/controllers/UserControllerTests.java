package com.example.finalproject.controllers;

import com.example.finalproject.entity.User;
import com.example.finalproject.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    Logger logger = LoggerFactory.getLogger(UserControllerTests.class);

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {

    }

    @Test
    public void testGetBalanceById() {
        User user1 = Mockito.mock(User.class);
        user1.setId(1);
        user1.setBalance(BigDecimal.valueOf(1000));
        Mockito.when(userService.getBalance(1)).thenReturn(String.valueOf(user1.getBalance()));

        ResponseEntity response = userController.getBalanceById(1);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(String.valueOf(BigDecimal.valueOf(1000)), response.getBody());
    }

    @Test
    public void testTakeMoney() {
        User user1 = Mockito.mock(User.class);
        user1.setId(1);
        user1.setBalance(BigDecimal.valueOf(1000));

        BigDecimal amount = BigDecimal.valueOf(500);
        logger.info("Настройка поведения мок-сервиса");
        Mockito.when(userService.takeMoney(user1.getId(), amount)).thenReturn(user1);
        logger.info("Вызов метода контроллера");
        ResponseEntity response = userController.
                getTakeMoneyByBalance(user1.getId(), amount);
        logger.info("Проверка HttpStatus");
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity balanceResponse = userController.getBalanceById(user1.getId());
        Assert.assertEquals(HttpStatus.OK, balanceResponse.getStatusCode());
        logger.info("Проверка соответствия баланса");
        Assert.assertEquals(balanceResponse.getBody(), BigDecimal.valueOf(500));




        logger.info("Проверка вызова метода");
        Mockito.verify(userService, Mockito.times(1))
                .takeMoney(user1.getId(), amount);





        int result = user1.getBalance().compareTo(BigDecimal.ZERO);
        Assert.assertTrue(result >= 0);
    }

    @Test
    public void putMoneyTest() {

    }

    @Test
    public void getOperationListTest() {

    }

    @Test
    public void transferMoneyTest() {

    }
}
