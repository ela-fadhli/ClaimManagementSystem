package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.Claim;
import com.example.ClaimManagementSystem.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping("/create")
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {

        return ResponseEntity.ok(claimService.registerClaim(claim));
    }

    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }


    @GetMapping("/{claimId}")
    public ResponseEntity<Claim> getClaimWithDetails(@PathVariable String claimId) {
        return ResponseEntity.ok(claimService.findClaimById(claimId));
    }

    @PutMapping("/{claimId}")
    public ResponseEntity<Claim> updateClaim(
            @PathVariable String claimId,
            @RequestBody Claim updatedClaim
    ) {
        return ResponseEntity.ok(claimService.updateClaim(claimId, updatedClaim));
    }
}