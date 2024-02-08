package com.example.finalproject;

import com.example.finalproject.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);



        EntityManagerFactory emf = Persistence.createEntityManagerFactory("application.properties");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);

        em.getTransaction().commit();
        em.close();
    }

}
