package com.example.expense_tracker;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController 
@RequestMapping ("/api/expenses")
public class HelloController {
    
    private final ExpenseService expenseService;

    public HelloController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping 
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public Expense creatExpense(
        @Valid @RequestBody CreateExpenseRequest request) {
        return expenseService.addExpense(request);
    }

}
