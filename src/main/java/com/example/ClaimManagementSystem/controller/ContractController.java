package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.Contract;
import com.example.ClaimManagementSystem.model.dto.request.ContractCreateDTO;
import com.example.ClaimManagementSystem.model.dto.request.ContractUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.ContractDTO;
import com.example.ClaimManagementSystem.model.mapper.ContractMapper;
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
    private final ContractMapper contractMapper;

    @PostMapping("/register")
    public ResponseEntity<ContractDTO> registerContract(@RequestBody ContractCreateDTO contractCreateDTO) {
        Contract contract = contractMapper.ToEntityMapper(contractCreateDTO);
        contract.setUuid(UUID.randomUUID().toString());
        return ResponseEntity.ok(contractMapper.ToDtoMapper(contractService.registerContract(contract)));
    }

    @GetMapping("/{contractUuid}")
    public ResponseEntity<ContractDTO> getContractProfile(@PathVariable String contractUuid) {
        try {
            return ResponseEntity.ok(contractMapper.ToDtoMapper(contractService.findContractByUuid(contractUuid)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{contractUuid}")
    public ResponseEntity<ContractDTO> updateContract(
            @PathVariable String contractUuid,
            @RequestBody ContractUpdateDTO updatedContract
    ) {
        return ResponseEntity.ok(contractMapper.ToDtoMapper(contractService.updateContract(contractUuid, updatedContract)));
    }

    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        return ResponseEntity.ok(contractMapper.ToDtoMapper(contractService.getAllContracts()));
    }
}