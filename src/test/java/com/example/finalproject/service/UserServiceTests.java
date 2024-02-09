package com.example.finalproject.service;

import com.example.finalproject.entity.BaseOfOperation;
import com.example.finalproject.entity.User;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTests {

    @Test
    public void getBalanceTest() {
        User user = new User();
        user.setBalance(BigDecimal.valueOf(1000));

        BigDecimal balance = user.getBalance();

        assertEquals(BigDecimal.valueOf(1000), balance);
    }

    @Test
    public void takeMoneyTest() {
        User user = new User();
        user.setBalance(BigDecimal.valueOf(1000));

        BigDecimal amount = BigDecimal.valueOf(500);
        user.setBalance(user.getBalance().subtract(amount));

        assertEquals(BigDecimal.valueOf(500), user.getBalance());
    }

    @Test
    public void putMoneyTest() {
        User user = new User();
        user.setBalance(BigDecimal.valueOf(1000));

        BigDecimal amount = BigDecimal.valueOf(1500);
        user.setBalance(user.getBalance().add(amount));

        assertEquals(BigDecimal.valueOf(2500), user.getBalance());
    }

    @Test
    public void getOperationListTest() {
        User user1 = new User();
        user1.setId(1L);
        user1.setBalance(BigDecimal.valueOf(1000));
        User user2 = new User();
        user2.setId(2L);
        user2.setBalance(BigDecimal.valueOf(2000));
        List<BaseOfOperation> baseOfOperations = new ArrayList<>();

        baseOfOperations.add(new BaseOfOperation());
        baseOfOperations.add(new BaseOfOperation());

        baseOfOperations.get(0).setUser(user1);
        baseOfOperations.get(1).setUser(user2);

        List<BaseOfOperation> listOperations = baseOfOperations.stream()
                .filter(operation -> operation.getUser() !=null &&
                        (operation.getUser().equals(user1)) || operation.getUser().equals(user2))
                .collect(Collectors.toList());

        assertEquals(2, listOperations.size());
    }

    @Test
    public void transferMoneyTest() {
        User sender = new User();
        sender.setBalance(BigDecimal.valueOf(1000));

        User recipient = new User();
        recipient.setBalance(BigDecimal.valueOf(2000));

        BigDecimal amount = BigDecimal.valueOf(500);
        sender.setBalance(sender.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));

        assertEquals(BigDecimal.valueOf(500), sender.getBalance());
        assertEquals(BigDecimal.valueOf(2500), recipient.getBalance());
    }
}
