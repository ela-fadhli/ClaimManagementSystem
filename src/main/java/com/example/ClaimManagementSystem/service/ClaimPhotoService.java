package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.model.dto.PhotoDTO;
import java.util.List;

public interface ClaimPhotoService {
    List<ClaimPhoto> getClaimPhotos(String claimUuid);
    PhotoDTO getPhoto(String photoUuid);
}
