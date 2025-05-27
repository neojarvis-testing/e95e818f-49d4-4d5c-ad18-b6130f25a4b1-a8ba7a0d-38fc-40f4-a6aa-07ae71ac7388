package com.examly.springmutualfund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springmutualfund.model.MutualFund;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund,Long>  {
    List<MutualFund> findByFundName(String fundName);
}
