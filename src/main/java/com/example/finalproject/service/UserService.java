package com.example.finalproject.service;

import com.example.finalproject.entity.BaseOfOperation;
import com.example.finalproject.entity.User;
import com.example.finalproject.repository.OperationRepository;
import com.example.finalproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;

    public UserService(UserRepository userRepository, OperationRepository operationRepository) {
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
    }

    @Transactional
    public BigDecimal getBalance(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return user.getBalance();
    }

    @Transactional
    public User takeMoney(long id, BigDecimal money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (user.getBalance().compareTo(money) >= 0) {
            user.setBalance(user.getBalance().subtract(money));

        } else {
            throw new IllegalArgumentException("Недостаточно средств на счету");
        }
        BaseOfOperation baseOfOperations = new BaseOfOperation();
        baseOfOperations.setUser(user);
        baseOfOperations.setType_operation(1);
        baseOfOperations.setAmount(money);
        baseOfOperations.setTimeOperation(new Date());
        operationRepository.save(baseOfOperations);
        userRepository.save(user);
        return user;
    }
    @Transactional
    public String putMoney(long id, BigDecimal money) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        user.setBalance(user.getBalance().subtract(money));
        BaseOfOperation baseOfOperations = new BaseOfOperation();
        baseOfOperations.setUser(user);
        baseOfOperations.setType_operation(1);
        baseOfOperations.setAmount(money);
        baseOfOperations.setTimeOperation(new Date());
        operationRepository.save(baseOfOperations);
        userRepository.save(user);
        return "Успех (1)";
    }
    @Transactional
    public List<String> getOperationList(long id, Date beginDate, Date endDate) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        List<String> result = new ArrayList<>();

        if (beginDate == null || endDate == null) {
            List<BaseOfOperation> list = operationRepository.findOperationsByUserId(user.getId());
            for (BaseOfOperation e : list) {
                result.add("Cумма: " + String.valueOf(e.getAmount()));
                result.add("Тип операции: " + String.valueOf(e.getType_operation()));
                result.add("Время операции: " + String.valueOf(e.getTimeOperation()));
            }
            return result;
        } else {
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
    public String transferMoney(long sender_id, long recipient_id, BigDecimal money) {
        User sender = userRepository.findById(sender_id).orElseThrow(() -> new EntityNotFoundException());
        User recipient = userRepository.findById(recipient_id).orElseThrow(() -> new EntityNotFoundException());
        if(sender.getBalance().compareTo(money) >= 0) {
            sender.setBalance(sender.getBalance().subtract(money));
            recipient.setBalance(recipient.getBalance().add(money));
            userRepository.save(sender);
            userRepository.save(recipient);
            return "Успех (1)";
        }else {
            return "Ошибка при выполнении операции";
        }

    }
}
