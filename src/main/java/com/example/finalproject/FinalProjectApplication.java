package com.example.finalproject;

import com.example.finalproject.entity.User;
import com.example.finalproject.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {

    private static UserRepository userRepository;

    public FinalProjectApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
        User user1 = new User();
        user1.setBalance(1000);
        User user2 = new User();
        user1.setBalance(5000);
        User user3 = new User();
        user1.setBalance(3000);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
