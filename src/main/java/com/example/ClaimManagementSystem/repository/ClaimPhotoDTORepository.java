package com.example.ClaimManagementSystem.repository;


import com.example.ClaimManagementSystem.model.ClaimPhotoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimPhotoDTORepository extends JpaRepository<ClaimPhotoDTO,Long> {
}
