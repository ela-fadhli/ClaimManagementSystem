package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ContractService {
    @Autowired
    private final ContractRepository contractRepository;

    @Transactional
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Transactional
    public Contract registerContract(Contract contract) {
        return contractRepository.save((contract));
    }

    @Transactional
    public Contract updateContract(long contractId, Contract updatedContract) {
        Contract existingContract = contractRepository.findById(contractId)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        if(updatedContract.getStartDate() != null) {
            existingContract.setStartDate(updatedContract.getStartDate());
        }
        if(updatedContract.getEndDate() != null) {
            existingContract.setEndDate(updatedContract.getEndDate());
        }
        if(updatedContract.getInsuredId() != null) {
            existingContract.setInsuredId(updatedContract.getInsuredId());
        }
        if(updatedContract.getVehiclePlate() != null) {
            existingContract.setVehiclePlate(updatedContract.getVehiclePlate());
        }

        return contractRepository.save(existingContract);
    }

    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }
}