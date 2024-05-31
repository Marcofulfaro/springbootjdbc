package com.example.springportfolio.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private LocalDate date;


}

