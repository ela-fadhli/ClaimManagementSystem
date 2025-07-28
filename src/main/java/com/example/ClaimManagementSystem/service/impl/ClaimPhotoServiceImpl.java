package com.example.ClaimManagementSystem.service.impl;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.model.dto.PhotoDTO;
import com.example.ClaimManagementSystem.repository.ClaimPhotoRepository;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import com.example.ClaimManagementSystem.service.ClaimPhotoService;
import com.example.ClaimManagementSystem.service.UserService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimPhotoServiceImpl implements ClaimPhotoService {
    final private ClaimPhotoRepository photoRepository;
    final private ClaimRepository claimRepository;

    @Override
    public List<ClaimPhoto> getClaimPhotos(String claimUuid){
        return photoRepository.findByClaimId(claimRepository.findByUuid(claimUuid).getId());
    }

    @Override
    public PhotoDTO getPhoto(String photoUuid){

        try {
            ClaimPhoto photo;
            try {
                photo = photoRepository.findByUuid(photoUuid);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
            byte[] imageBytes = Files.readAllBytes(Paths.get(photo.getPath()));
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            PhotoDTO claimPhotoDTO = new PhotoDTO(
                    photo.getId(), base64
            );

            return claimPhotoDTO;

        } catch (IOException | java.io.IOException e) {
            throw new RuntimeException(e);
        }

    }
}
