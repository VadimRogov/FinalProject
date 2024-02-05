package com.example.finalproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "base_operation")
@Data
public class BaseOfOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_operation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @Column
    private int type_operation;
    @Column
    private int amount;
}
