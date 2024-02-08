package com.example.finalproject;

import com.example.finalproject.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class FinalProjectApplication implements CommandLineRunner {
    private final EntityManager entityManager;

    public FinalProjectApplication(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        entityManager.getTransaction().begin();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManager.close();
    }
}
