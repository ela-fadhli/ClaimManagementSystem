package com.example.ClaimManagementSystem.repository;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimPhotoRepository extends JpaRepository<ClaimPhoto, Long> {
    List<ClaimPhoto> findByClaimId(Long claimId);
}