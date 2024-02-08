package com.example.finalproject.service;

import com.example.finalproject.entity.BaseOfOperation;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public User getBalance(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
    @Transactional
    public User takeMoney(long id, long money) {
        logger.debug("Поиск пользователя");
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        logger.debug("пользователь найден");
        if (user.getBalance() >= money) {
            user.setBalance(user.getBalance() - money);
        } else {
            throw new IllegalArgumentException("Недостаточно средств на счету");
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
        baseOfOperations.setTimeOperation(new Date());
        operationRepository.save(baseOfOperations);
        logger.debug("Операции сохранены");
        return userRepository.save(user);
    }
    @Transactional
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
        baseOfOperations.setTimeOperation(new Date());
        operationRepository.save(baseOfOperations);
        logger.debug("Операции сохранены");
        return userRepository.save(user);
    }
    @Transactional
    public List<String> getOperationList(long id, Date beginDate, Date endDate) {
        logger.info("Поиск пользователя");
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        logger.info("Пользователь найден");
        List<String> result = new ArrayList<>();

        if (beginDate == null || endDate == null) {
            logger.info("Выборка всех значений");
            List<BaseOfOperation> list = operationRepository.findOperationsByUserId(user.getId());
            for (BaseOfOperation e : list) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + String.valueOf(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        } else {
            logger.info("Выборка по id и диапозону дат");
            List<BaseOfOperation> listRange =
                    operationRepository.findOperationsByUserIdAndDateRange(user.getId(), beginDate, endDate);
            for (BaseOfOperation e : listRange) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + String.valueOf(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        }
    }
    @Transactional
    public String transferMoney(long sender_id, long recipient_id, long money) {
        User sender = userRepository.findById(sender_id).orElseThrow(() -> new EntityNotFoundException());
        User recipient = userRepository.findById(recipient_id).orElseThrow(() -> new EntityNotFoundException());
        if(sender.getBalance() >= money) {
            sender.setBalance(sender.getBalance() - money);
            recipient.setBalance(recipient.getBalance() + money);
            userRepository.save(sender);
            userRepository.save(recipient);
            return "Успех (1)";
        }else {
            return "Ошибка при выполнении операции";
        }

    }
}
