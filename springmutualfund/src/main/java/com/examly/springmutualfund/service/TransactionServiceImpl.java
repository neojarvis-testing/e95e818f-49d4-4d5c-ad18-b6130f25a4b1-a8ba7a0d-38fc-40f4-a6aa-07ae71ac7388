package com.examly.springmutualfund.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springmutualfund.model.Transaction;
import com.examly.springmutualfund.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
   
    @Override
    public Transaction saveTransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Optional<Transaction> tempTransaction = transactionRepository.findById(id);
        if(tempTransaction.isPresent()){
            Transaction transaction = tempTransaction.get();
            transaction.setStatus(updatedTransaction.getStatus());
            transaction.setAmount(updatedTransaction.getAmount());
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> geTransactionsByInvestorId(Long investorId) {
        return transactionRepository.findByInvestorId(investorId);
    }
}
