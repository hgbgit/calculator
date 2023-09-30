package com.loanpro.calculator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Operation operation;

    @ManyToOne
    private User user;

    @Column
    private BigDecimal amount;

    @Column
    private BigDecimal userBalance;

    @Column
    private String operationResponse;

    @CreatedDate
    private LocalDateTime date;
}