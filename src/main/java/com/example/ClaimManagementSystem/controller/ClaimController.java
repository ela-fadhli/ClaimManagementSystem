package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.Claim;
import com.example.ClaimManagementSystem.model.dto.request.ClaimCreateDTO;
import com.example.ClaimManagementSystem.model.dto.request.ClaimUpdateDTO;
import com.example.ClaimManagementSystem.model.dto.response.ClaimDTO;
import com.example.ClaimManagementSystem.model.mapper.ClaimMapper;
import com.example.ClaimManagementSystem.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/claim")
@RequiredArgsConstructor
public class ClaimController {
    private final ClaimService claimService;
    private final ClaimMapper claimMapper;

    @PostMapping("/create")
    public ResponseEntity<ClaimDTO> createClaim(@RequestBody ClaimCreateDTO claim) {
        return ResponseEntity.ok(claimMapper.ToDtoMapper(claimService.registerClaim(claimMapper.ToEntityMapper(claim))));
    }

    @GetMapping
    public ResponseEntity<List<ClaimDTO>> getAllClaims() {
        return ResponseEntity.ok(claimMapper.ToDtoMapper(claimService.getAllClaims()));
    }


    @GetMapping("/{claimUuid}")
    public ResponseEntity<ClaimDTO> getClaimWithDetails(@PathVariable String claimUuid) {
        return ResponseEntity.ok(claimMapper.ToDtoMapper(claimService.findClaimById(claimUuid)));
    }

    @PutMapping("/{claimUuid}")
    public ResponseEntity<ClaimDTO> updateClaim(
            @PathVariable String claimUuid,
            @RequestBody ClaimUpdateDTO updatedClaim
    ) {
        return ResponseEntity.ok(claimMapper.ToDtoMapper(claimService.updateClaim(claimUuid, claimMapper.ToEntityMapper(updatedClaim, claimService.findClaimById(claimUuid)))));
    }
}