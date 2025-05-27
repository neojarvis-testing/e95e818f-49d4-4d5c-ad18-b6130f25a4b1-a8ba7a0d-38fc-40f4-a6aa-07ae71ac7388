package com.examly.springmutualfund.service;

import java.util.List;

import com.examly.springmutualfund.model.MutualFund;

public interface MutualFundService {
    MutualFund saveMutualFund(MutualFund mutualFund);
    List<MutualFund> getAllMutualFunds();
    MutualFund getMutualFundById(Long id);
    MutualFund updateMutualFund(Long id, MutualFund mutualFund);
    void deleteMutualFund(Long id);
}
