package com.examly.springmutualfund.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springmutualfund.model.MutualFund;
import com.examly.springmutualfund.repository.MutualFundRepository;

@Service
public class MutualFundServiceImpl implements MutualFundService {

    private MutualFundRepository mutualFundRepository;

    public MutualFundRepository getMutualFundRepository() {
        return mutualFundRepository;
    }

    public void setMutualFundRepository(MutualFundRepository mutualFundRepository) {
        this.mutualFundRepository = mutualFundRepository;
    }

    @Override
    public MutualFund saveMutualFund(MutualFund mutualFund) {
        return mutualFundRepository.save(mutualFund);
    }

    @Override
    public List<MutualFund> getAllMutualFunds() {
        return mutualFundRepository.findAll();
    }

    @Override
    public MutualFund getMutualFundById(Long id) {
        return mutualFundRepository.findById(id).orElse(null);
    }

    @Override
    public MutualFund updateMutualFund(Long id, MutualFund updatedFund) {
        Optional<MutualFund> existingfund = mutualFundRepository.findById(id);
        if(existingfund.isPresent()){
            MutualFund fund = existingfund.get();
            fund.setFundName(updatedFund.getFundName());
            fund.setFundManager(updatedFund.getFundManager());
            fund.setCurrentNAV(updatedFund.getCurrentNAV());
            fund.setExpenseRatio(updatedFund.getExpenseRatio());
            fund.setProspectus(updatedFund.getProspectus());

            return mutualFundRepository.save(fund);
        }
        return null;
     }

    @Override
    public void deleteMutualFund(Long id) {
        mutualFundRepository.deleteById(id);
     }

    

}
