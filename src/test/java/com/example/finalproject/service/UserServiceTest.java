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
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    OperationRepository operationRepository;


    @Test
    public void testGetBalance() {
        // Arrange
        long userId = 1L;
        BigDecimal expectedBalance = new BigDecimal(1000);
        User user = new User();
        user.setId(userId);
        user.setBalance(expectedBalance);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        BigDecimal actualBalance = userService.getBalance(userId);

        // Assert
        Assertions.assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testTakeMoney() {
        long userId = 1L;
        BigDecimal money = BigDecimal.valueOf(500);

        User user = User.builder()
                .id(userId)
                .balance(BigDecimal.valueOf(1000))
                .build();

        BaseOfOperation baseOfOperation = BaseOfOperation.builder()
                .id_operation(1)
                .user(user)
                .type_operation(1)
                .amount(money)
                .timeOperation(new Date())
                .build();

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(operationRepository.save(Mockito.any(BaseOfOperation.class))).thenReturn(baseOfOperation);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User result = userService.takeMoney(userId, money);

        Assertions.assertEquals(user, result);
        Assertions.assertEquals(BigDecimal.valueOf(500), result.getBalance());
        Mockito.verify(operationRepository, Mockito.times(1)).save(Mockito.any(BaseOfOperation.class));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testPutMoney() {
        long userId = 1L;
        BigDecimal money = BigDecimal.valueOf(500);

        User user = User.builder()
                .id(userId)
                .balance(BigDecimal.valueOf(1000))
                .build();

        BaseOfOperation baseOfOperation = BaseOfOperation.builder()
                .id_operation(1)
                .user(user)
                .type_operation(1)
                .amount(money)
                .timeOperation(new Date())
                .build();

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(operationRepository.save(Mockito.any(BaseOfOperation.class))).thenReturn(baseOfOperation);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        String result = userService.putMoney(userId, money);

        Assertions.assertEquals("Успех (1)", result);
        Assertions.assertEquals(BigDecimal.valueOf(500), user.getBalance());
        Mockito.verify(operationRepository, Mockito.times(1)).save(Mockito.any(BaseOfOperation.class));
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testGetOperationList() {
        long userId = 1L;
        Date beginDate = null;
        Date endDate = null;

        User user = User.builder()
                .id(userId)
                .balance(BigDecimal.valueOf(1000))
                .build();

        BaseOfOperation baseOfOperation1 = BaseOfOperation.builder()
                .id_operation(1)
                .user(user)
                .type_operation(1)
                .amount(BigDecimal.valueOf(500))
                .timeOperation(new Date())
                .build();

        BaseOfOperation baseOfOperation2 = BaseOfOperation.builder()
                .id_operation(2)
                .user(user)
                .type_operation(2)
                .amount(BigDecimal.valueOf(1000))
                .timeOperation(new Date())
                .build();

        List<BaseOfOperation> listOperations = new ArrayList<>();
        listOperations.add(baseOfOperation1);
        listOperations.add(baseOfOperation2);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(operationRepository.findOperationsByUserId(user.getId())).thenReturn(listOperations);

        List<String> expected = new ArrayList<>();
        expected.add("Cумма: 500");
        expected.add("Тип операции: 1");
        expected.add("Время операции: " + baseOfOperation1.getTimeOperation().toString());
        expected.add("Cумма: 1000");
        expected.add("Тип операции: 2");
        expected.add("Время операции: " + baseOfOperation2.getTimeOperation().toString());

        List<String> result = userService.getOperationList(userId, beginDate, endDate);

        Assertions.assertEquals(expected, result);
        Mockito.verify(operationRepository, Mockito.times(1)).findOperationsByUserId(user.getId());
    }

    @Test
    public void testTransferMoneySufficientFunds() {
        long senderId = 1L;
        long recipientId = 2L;
        BigDecimal money = BigDecimal.valueOf(500);

        User sender = User.builder()
                .id(senderId)
                .balance(BigDecimal.valueOf(1000))
                .build();

        User recipient = User.builder()
                .id(recipientId)
                .balance(BigDecimal.valueOf(500))
                .build();

        Mockito.when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
        Mockito.when(userRepository.findById(recipientId)).thenReturn(Optional.of(recipient));

        String result = userService.transferMoney(senderId, recipientId, money);

        Assertions.assertEquals("Успех (1)", result);
        Assertions.assertEquals(BigDecimal.valueOf(500), sender.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(1000), recipient.getBalance());
        Mockito.verify(userRepository, Mockito.times(1)).save(sender);
        Mockito.verify(userRepository, Mockito.times(1)).save(recipient);
    }

    @Test
    public void testTransferMoneyInsufficientFunds() {
        long senderId = 1L;
        long recipientId = 2L;
        BigDecimal money = BigDecimal.valueOf(1500);

        User sender = User.builder()
                .id(senderId)
                .balance(BigDecimal.valueOf(1000))
                .build();

        User recipient = User.builder()
                .id(recipientId)
                .balance(BigDecimal.valueOf(500))
                .build();

        Mockito.when(userRepository.findById(senderId)).thenReturn(Optional.of(sender));
        Mockito.when(userRepository.findById(recipientId)).thenReturn(Optional.of(recipient));

        String result = userService.transferMoney(senderId, recipientId, money);

        Assertions.assertEquals("Ошибка при выполнении операции", result);
        Assertions.assertEquals(BigDecimal.valueOf(1000), sender.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(500), recipient.getBalance());
        Mockito.verify(userRepository, Mockito.never()).save(sender);
        Mockito.verify(userRepository, Mockito.never()).save(recipient);
    }
}