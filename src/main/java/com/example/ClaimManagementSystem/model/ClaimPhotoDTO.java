package com.example.ClaimManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClaimPhotoDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String base64Image; // temporary field just for sending to frontend
}