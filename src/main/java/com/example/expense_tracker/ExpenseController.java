package com.example.expense_tracker;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController 
@RequestMapping ("/api/expenses")
public class ExpenseController {
    
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping 
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public Expense creatExpense(@Valid @RequestBody CreateExpenseRequest request) {
        return expenseService.addExpense(request);
    }

    @GetMapping ("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense
        (
            @PathVariable Long id,
            @Valid @RequestBody CreateExpenseRequest request
        ) 
        {
            return expenseService.updateExpense(id, request);
        }
    
    @DeleteMapping ("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long id) {  
        expenseService.deleteExpense(id);
    }
    
}
