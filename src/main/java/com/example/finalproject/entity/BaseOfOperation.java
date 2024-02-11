package com.example.finalproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "base_operation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseOfOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_operation;
    @ManyToOne
    private User user;
    @Column
    private int type_operation;
    @Column
    private BigDecimal amount;
    @Column
    private Date timeOperation;
}
