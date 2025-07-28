package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.model.dto.PhotoDTO;
import com.example.ClaimManagementSystem.model.dto.response.ClaimPhotoDTO;

import java.util.List;
import java.util.UUID;

public interface ClaimPhotoService {
    List<ClaimPhoto> getClaimPhotos(String claimUuid);
    PhotoDTO getPhoto(String photoUuid);
}
