package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.model.dto.ClaimPhotoDTO;
import com.example.ClaimManagementSystem.repository.ClaimPhotoRepository;
import com.example.ClaimManagementSystem.service.ClaimPhotoStorageService;
import io.jsonwebtoken.io.IOException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/claims/{claimId}/photos")
public class ClaimPhotoController {

    private final ClaimPhotoStorageService storageService;
    private final ClaimPhotoRepository photoRepository;

    public ClaimPhotoController(ClaimPhotoStorageService storageService,
                                ClaimPhotoRepository photoRepository)  {
        this.storageService = storageService;
        this.photoRepository = photoRepository;
    }

    @PostMapping
    public ResponseEntity<ClaimPhoto> uploadPhoto(
            @PathVariable Long claimId,
            @RequestParam("file") MultipartFile file
    ) throws IOException, java.io.IOException {

        ClaimPhoto photo = storageService.storeFile(file, claimId);
        ClaimPhoto savedPhoto = photoRepository.save(photo);

        return ResponseEntity.ok(savedPhoto);
    }

    /*@GetMapping("/{photoId}")
    public ResponseEntity<byte[]> getPhoto(
            @PathVariable Long claimId,
            @PathVariable Long photoId) throws IOException, java.io.IOException {

        ClaimPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        Path filePath = storageService.loadFile(claimId, photo.getPath());
        byte[] fileContent = Files.readAllBytes(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photo.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + photo.getOriginalFileName() + "\"")
                .body(fileContent);
    }*/

    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(
            @PathVariable Long claimId,
            @PathVariable Long photoId) throws IOException, java.io.IOException {

        ClaimPhoto photo = photoRepository.findById(photoId)
                .orElseThrow(() -> new RuntimeException("Photo not found"));

        storageService.deleteFile(claimId, photo.getPath());
        photoRepository.delete(photo);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClaimPhoto>> getClaimPhotos(@PathVariable Long claimId) {
        List<ClaimPhoto> photos = photoRepository.findByClaimId(claimId);
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<ClaimPhotoDTO> getClaimPhotoById(@PathVariable Long photoId) {
        Optional<ClaimPhoto> optionalPhoto = photoRepository.findById(photoId);

        if (optionalPhoto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ClaimPhoto photo = optionalPhoto.get();

        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(photo.getPath()));
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            ClaimPhotoDTO claimPhotoDTO = new ClaimPhotoDTO(
                    photo.getId(),base64
            );

            return ResponseEntity.ok().body(claimPhotoDTO);

        } catch (IOException | java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}