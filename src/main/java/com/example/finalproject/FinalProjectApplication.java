package com.example.finalproject;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.math.BigDecimal;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

    private static UserRepository userRepository;

    public FinalProjectApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);

        User user1 = new User();
        user1.setBalance(BigDecimal.valueOf(1000));
        User user2 = new User();
        user2.setBalance(BigDecimal.valueOf(3000));
        User user3 = new User();
        user3.setBalance(BigDecimal.valueOf(5000));

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
