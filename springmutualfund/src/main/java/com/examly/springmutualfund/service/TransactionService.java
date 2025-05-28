package com.examly.springmutualfund.service;

import java.util.List;

import com.examly.springmutualfund.model.Transaction;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(Long id);
    Transaction updateTransaction(Long id, Transaction transaction);
    void deleteTransaction(Long id);
    List<Transaction> geTransactionsByInvestorId(Long investorId);
}
