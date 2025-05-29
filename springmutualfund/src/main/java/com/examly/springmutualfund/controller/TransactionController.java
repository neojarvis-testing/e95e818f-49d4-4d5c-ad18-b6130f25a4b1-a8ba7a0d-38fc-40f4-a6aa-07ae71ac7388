package com.examly.springmutualfund.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springmutualfund.model.Transaction;
import com.examly.springmutualfund.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return ResponseEntity.status(201).body(savedTransaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<List<Transaction>> getTransactionByInvestor(@PathVariable Long investorId){
        return ResponseEntity.ok(transactionService.geTransactionsByInvestorId(investorId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Transaction> updateStatus(@PathVariable Long id, @RequestParam String status){
        Transaction transaction = transactionService.getTransactionById(id);
        transaction.setStatus(status);
        Transaction updatedTransaction = transactionService.updateTransaction(id,transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    

    
    
    
}
