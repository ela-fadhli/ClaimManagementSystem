package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.model.dto.request.ContractUpdateDTO;
import com.example.ClaimManagementSystem.model.mapper.ContractMapper;
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
    @Autowired
    private ContractMapper contractMapper;

    @Transactional
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Transactional
    public Contract registerContract(Contract contract) {
        return contractRepository.save((contract));
    }

    @Transactional
    public Contract updateContract(String contractUuid, ContractUpdateDTO updatedContract) {

        Contract existingContract = contractRepository.findByUuid(contractUuid);

        contractMapper.ToEntityMapper(updatedContract, existingContract);

        return contractRepository.save(existingContract);

    }

    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    public Contract findContractByUuid(String contractUuid) {
        return contractRepository.findByUuid(contractUuid);
    }
}