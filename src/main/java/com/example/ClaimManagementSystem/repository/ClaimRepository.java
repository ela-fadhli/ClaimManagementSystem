package com.example.ClaimManagementSystem.repository;

import com.example.ClaimManagementSystem.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long>{

    Claim findByUuid(String uuid);

    default Long findContractIdByUuid(String uuid){
        return findByUuid(uuid).getContractId();
    }
}