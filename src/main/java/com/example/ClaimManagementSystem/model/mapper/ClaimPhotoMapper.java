package com.example.ClaimManagementSystem.model.mapper;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.model.dto.response.ClaimPhotoDTO;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClaimPhotoMapper {

    private final ClaimRepository claimRepository;

    public ClaimPhotoMapper(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public ClaimPhotoDTO ToDtoMapper(ClaimPhoto claimPhoto) {
        return new ClaimPhotoDTO(claimPhoto.getUuid(), claimPhoto.getPath(), claimPhoto.getOriginalFileName(), claimPhoto.getContentType(),claimRepository.findById(claimPhoto.getClaimId()).orElseThrow(() -> new RuntimeException("claim not found with id: " + claimPhoto.getClaimId())).getUuid() , claimPhoto.getSize(), claimPhoto.getCreatedAt());
    }

    public List<ClaimPhotoDTO> ToDtoMapper(List<ClaimPhoto> claimPhotos) {
        List<ClaimPhotoDTO> claimPhotoDTOList = new ArrayList<>();
        for(ClaimPhoto claimPhoto : claimPhotos) {
            claimPhotoDTOList.add(ToDtoMapper(claimPhoto));
        }
        return claimPhotoDTOList;
    }
}
