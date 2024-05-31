package com.example.springportfolio.controller;

import com.example.springportfolio.model.Transaction;
import com.example.springportfolio.model.TransactionType;
import com.example.springportfolio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        model.addAttribute("balance", transactionService.calculateBalance());
        return "movimenti";
    }

    @GetMapping("/new")
    public String showAddTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("types", TransactionType.values());
        return "add";
    }

    @PostMapping
    public String addTransaction(@ModelAttribute Transaction transaction) {
        transactionService.addTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }


}
