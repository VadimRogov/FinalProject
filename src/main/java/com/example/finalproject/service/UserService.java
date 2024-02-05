package com.example.finalproject.service;

import com.example.finalproject.entity.BaseOfOperation;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;

    public UserService(UserRepository userRepository, OperationRepository operationRepository) {
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
    }


    public User getBalance(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public User takeMoney(long id, long money) {
        logger.debug("Поиск пользователя");
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        logger.debug("пользователь найден");
        if(user.getBalance() >= money) {
            user.setBalance(user.getBalance() - money);
        }else {
            throw new IllegalArgumentException();
        }
        logger.info("Создаём сущность таблицы операции");
        BaseOfOperation baseOfOperations = new BaseOfOperation();
        logger.debug("Сущность успешно создана");
        baseOfOperations.setUser(user);
        logger.debug("user_id присвоенно");
        baseOfOperations.setType_operation(1);
        logger.debug("Тип операции присвоен");
        baseOfOperations.setAmount(money);
        logger.debug("Сумма присвоенно");
        operationRepository.save(baseOfOperations);
        logger.debug("Операции сохранены");
        return userRepository.save(user);
    }

    public User putMoney(long id, long money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        user.setBalance(user.getBalance() + money);
        logger.info("Создаём сущность таблицы операции");
        BaseOfOperation baseOfOperations = new BaseOfOperation();
        logger.debug("Сущность успешно создана");
        baseOfOperations.setUser(user);
        logger.debug("user_id присвоенно");
        baseOfOperations.setType_operation(1);
        logger.debug("Тип операции присвоен");
        baseOfOperations.setAmount(money);
        logger.debug("Сумма присвоенно");
        operationRepository.save(baseOfOperations);
        logger.debug("Операции сохранены");
        return userRepository.save(user);
    }

    public List<String> getOperationList(long id, Date beginDate, Date endDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        BaseOfOperation baseOfOperation = new BaseOfOperation();
    }
}
