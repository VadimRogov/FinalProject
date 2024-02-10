package com.example.finalproject.service;

import com.example.finalproject.entity.BaseOfOperation;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        user1.setId(1L);
        logger.info("Инициализируем баланс");
        user1.setBalance(BigDecimal.valueOf(1000));
        logger.info("Описываем поведение");
        Mockito.when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        logger.info("Вызываем метод userService получаем баланс");
        User user2 = userRepository.findById(user1.getId()).orElseThrow();

        int result = user2.getBalance().compareTo(BigDecimal.valueOf(1000));
        assertEquals(result, 0);
    }

    @Test
    public void testOperationRepository() {
        logger.info("Создаём пользователя");
        User user1 = new User();
        user1.setId(1L);
        user1.setBalance(BigDecimal.valueOf(1000));

        logger.info("Создаём пользователя");
        User user2 = new User();
        user1.setId(2L);
        user1.setBalance(BigDecimal.valueOf(3000));

        logger.info("Создаём операцию 1");
        BaseOfOperation baseOfOperation1 = new BaseOfOperation();
        baseOfOperation1.setUser(user1);
        baseOfOperation1.setType_operation(1);
        baseOfOperation1.setTimeOperation(new Date());
        baseOfOperation1.setAmount(BigDecimal.valueOf(1000));

        logger.info("Создаём операцию 2");
        BaseOfOperation baseOfOperation2 = new BaseOfOperation();
        baseOfOperation2.setUser(user2);
        baseOfOperation2.setType_operation(2);
        baseOfOperation2.setTimeOperation(new Date());
        baseOfOperation2.setAmount(BigDecimal.valueOf(2000));

        logger.info("Список всех операций");
        List<BaseOfOperation> listOperations = new ArrayList<>();
        listOperations.add(baseOfOperation1);
        listOperations.add(baseOfOperation2);
        logger.info("Настраиваем поведение метода");
        Mockito.when(operationRepository.findOperationsByUserId(user1.getId())).thenReturn(listOperations);
        logger.info("Проверка");

        List<BaseOfOperation> listOperations1 = new ArrayList<>();
        listOperations1.add(baseOfOperation2);

        Assertions.assertEquals(operationRepository.findOperationsByUserId(user1.getId()), listOperations
                .stream()
                .filter(user -> user1.equals(baseOfOperation1.getUser()))
                .collect(Collectors.toList()));
    }


}
