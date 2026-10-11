package com.example.expense_tracker;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense addExpense(CreateExpenseRequest request) {
        Expense expense = new Expense(
            null,
            request.getTitle(),
            request.getAmount(),
            request.getCategory(),
            request.getExpenseDate()
        );

        return expenseRepository.save(expense);
    }
}
