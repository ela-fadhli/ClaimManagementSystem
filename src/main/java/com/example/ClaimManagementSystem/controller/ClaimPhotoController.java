package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.dto.PhotoDTO;
import com.example.ClaimManagementSystem.model.dto.response.ClaimPhotoDTO;
import com.example.ClaimManagementSystem.model.mapper.ClaimPhotoMapper;
import com.example.ClaimManagementSystem.service.ClaimPhotoService;
import com.example.ClaimManagementSystem.service.ClaimPhotoStorageService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/claim-photo")
@RequiredArgsConstructor
public class ClaimPhotoController {

    private final ClaimPhotoStorageService storageService;
    private final ClaimPhotoMapper claimPhotoMapper;
    private final ClaimPhotoService claimPhotoService;


    @PostMapping("/{claimUuid}")
    public ResponseEntity<ClaimPhotoDTO> uploadPhoto(
            @PathVariable String claimUuid,
            @RequestParam("file") MultipartFile file
    ) throws IOException, java.io.IOException {
        return ResponseEntity.ok(claimPhotoMapper.ToDtoMapper(storageService.storeFile(file, claimUuid)));
    }

    @DeleteMapping("/{claimUuid}/{photoUuid}")
    public ResponseEntity<Void> deletePhoto(
            @PathVariable String claimUuid,
            @PathVariable String photoUuid) throws IOException, java.io.IOException {

        try {
            storageService.deleteFile(claimUuid, photoUuid);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{claimUuid}")
    public ResponseEntity<List<ClaimPhotoDTO>> getClaimPhotos(@PathVariable String claimUuid) {
        return ResponseEntity.ok(claimPhotoMapper.ToDtoMapper(claimPhotoService.getClaimPhotos(claimUuid)));
    }

    @GetMapping("/{claimUuid}/{photoUuid}")
    public ResponseEntity<PhotoDTO> getClaimPhotoByUuid(@PathVariable String photoUuid) {
            return ResponseEntity.ok().body(claimPhotoService.getPhoto(photoUuid));
    }
}