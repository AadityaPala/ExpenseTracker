package com.example.expense_tracker;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Expense not found with id: " + id
                ));
    }

    public Expense updateExpense(Long id, CreateExpenseRequest request) {
        Expense expense = expenseRepository.findById(id)
                            .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Expense not found with id: " + id
                            ));
        
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setExpenseDate(request.getExpenseDate());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                           .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND,
                               "Expense not found with id: " + id
                           ));
        
        expenseRepository.delete(expense);
    }
}
