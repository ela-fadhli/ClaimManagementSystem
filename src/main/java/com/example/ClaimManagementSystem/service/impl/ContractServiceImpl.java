package com.example.ClaimManagementSystem.service.impl;


import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.model.dto.request.ContractUpdateDTO;
import com.example.ClaimManagementSystem.model.mapper.ContractMapper;
import com.example.ClaimManagementSystem.repository.ContractRepository;
import com.example.ClaimManagementSystem.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private ContractMapper contractMapper;

    @Transactional
    @Override
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Transactional
    @Override
    public Contract registerContract(Contract contract) {
        return contractRepository.save((contract));
    }

    @Transactional
    @Override
    public Contract updateContract(String contractUuid, ContractUpdateDTO updatedContract) {

        Contract existingContract = contractRepository.findByUuid(contractUuid);

        contractMapper.ToEntityMapper(updatedContract, existingContract);

        return contractRepository.save(existingContract);

    }

    @Override
    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    @Override
    public Contract findContractByUuid(String contractUuid) {
        return contractRepository.findByUuid(contractUuid);
    }
}
