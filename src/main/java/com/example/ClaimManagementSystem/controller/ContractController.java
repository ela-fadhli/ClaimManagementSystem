package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @PostMapping("/register")
    public ResponseEntity<Contract> registerContract(@RequestBody Contract contract) {
        contract.setUuid(UUID.randomUUID().toString());
        return ResponseEntity.ok(contractService.registerContract(contract));
    }

    @GetMapping("/{contractId}")
    public ResponseEntity<Optional<Contract>> getContractProfile(@PathVariable Long contractId) {
        return ResponseEntity.ok(contractService.findById(contractId));
    }

    @PutMapping("/{contractId}")
    public ResponseEntity<Contract> updateContract(
            @PathVariable Long contractId,
            @RequestBody Contract updatedContract
    ) {
        return ResponseEntity.ok(contractService.updateContract(contractId, updatedContract));
    }

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {


        return ResponseEntity.ok(contractService.getAllContracts());
    }
}