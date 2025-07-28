package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.model.dto.request.ContractUpdateDTO;
import java.util.List;
import java.util.Optional;


public interface ContractService {


    List<Contract> getAllContracts();
    Contract registerContract(Contract contract);
    Contract updateContract(String contractUuid, ContractUpdateDTO updatedContract);
    Optional<Contract> findById(Long id);
    Contract findContractByUuid(String contractUuid);
}