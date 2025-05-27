package com.examly.springmutualfund.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springmutualfund.model.MutualFund;
import com.examly.springmutualfund.service.MutualFundService;

@RestController
@RequestMapping("/api/mutualFund")
public class MutualFundController {

    private MutualFundService mutualFundService;

    public MutualFundService getMutualFundService() {
        return mutualFundService;
    }

    public void setMutualFundService(MutualFundService mutualFundService) {
        this.mutualFundService = mutualFundService;
    }

    @PostMapping
    @PreAuthorize("hasRole('FUNDMANAGER')")
    public ResponseEntity<MutualFund> createMutualFund(@RequestBody MutualFund mutualFund){
        mutualFund.setDateCreated(LocalDateTime.now());
        MutualFund savedFund = mutualFundService.saveMutualFund(mutualFund);
        return ResponseEntity.status(201).body(savedFund);
    }

    @GetMapping
    public ResponseEntity<List<MutualFund>> getAllMutualFunds(){
        return ResponseEntity.ok(mutualFundService.getAllMutualFunds());
    }



    

    
}
