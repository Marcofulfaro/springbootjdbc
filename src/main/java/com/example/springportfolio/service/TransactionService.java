package com.example.springportfolio.service;

import com.example.springportfolio.model.Transaction;
import com.example.springportfolio.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteTransaction(id);
    }

    public BigDecimal calculateBalance() {
        List<Transaction> transactions = getAllTransactions();
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if ("entry".equalsIgnoreCase(String.valueOf(transaction.getType()))) {
                balance = balance.add(transaction.getAmount());
            } else if ("exit".equalsIgnoreCase(String.valueOf(transaction.getType()))) {
                balance = balance.subtract(transaction.getAmount());
            }
        }
        return balance;
    }
}
