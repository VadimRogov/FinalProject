package com.example.finalproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator")
    @GenericGenerator(name = "generator")
    @JsonProperty("id")
    private long id;

    @Column(name = "balans")
    @JsonProperty("balans")
    private long balans;
}
