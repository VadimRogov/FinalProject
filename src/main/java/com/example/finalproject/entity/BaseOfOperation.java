package com.example.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "base_operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseOfOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_operation;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;
    @Column
    private int type_operation;
    @Column
    private long amount;
    @Column
    private Date timeOperation;
}
