package com.example.finalproject.service;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private OperationRepository operationRepository;
    @MockBean
    private Logger loger = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void testGetBalance() {
        User user = new User();
        user.setId(1);
        user.setBalance(BigDecimal.valueOf(2000));
        loger.info("Устанавливаем правило поведения");
        when(userService.getBalance(user.getId())).thenReturn(String.valueOf(user.getBalance()));
        loger.info("Вызываем метод сервиса");
        BigDecimal balance =  new BigDecimal(userService.getBalance(user.getId()));
        loger.info("Результат сравнения BigDecimal");
        int result = balance.compareTo(user.getBalance());
        loger.info("Результирующее сравнение");
        assert result == 0;
    }


}
