package com.example.ClaimManagementSystem.repository;

import com.example.ClaimManagementSystem.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{
    Contract findByUuid(String Uuid);
}