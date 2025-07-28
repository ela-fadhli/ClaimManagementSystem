package com.example.ClaimManagementSystem.controller;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.model.dto.PhotoDTO;
import com.example.ClaimManagementSystem.model.dto.response.ClaimPhotoDTO;
import com.example.ClaimManagementSystem.model.mapper.ClaimPhotoMapper;
import com.example.ClaimManagementSystem.repository.ClaimPhotoRepository;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import com.example.ClaimManagementSystem.service.ClaimPhotoStorageService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/claims/{claimUuid}/photos")
@RequiredArgsConstructor
public class ClaimPhotoController {

    private final ClaimPhotoStorageService storageService;
    private final ClaimPhotoRepository photoRepository;
    private final ClaimPhotoMapper claimPhotoMapper;
    private final ClaimRepository claimRepository;

    /*public ClaimPhotoController(ClaimPhotoStorageService storageService,
                                ClaimPhotoRepository photoRepository, ClaimPhotoMapper claimPhotoMapper, ClaimRepository claimRepository) {
        this.storageService = storageService;
        this.photoRepository = photoRepository;
        this.claimPhotoMapper = claimPhotoMapper;
        this.claimRepository = claimRepository;
    }*/

    @PostMapping
    public ResponseEntity<ClaimPhotoDTO> uploadPhoto(
            @PathVariable String claimUuid,
            @RequestParam("file") MultipartFile file
    ) throws IOException, java.io.IOException {

        ClaimPhoto photo = storageService.storeFile(file, claimUuid);
        ClaimPhoto savedPhoto = photoRepository.save(photo);

        return ResponseEntity.ok(claimPhotoMapper.ToDtoMapper(savedPhoto));
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

    @DeleteMapping("/{photoUuid}")
    public ResponseEntity<Void> deletePhoto(
            @PathVariable String claimUuid,
            @PathVariable String photoUuid) throws IOException, java.io.IOException {

        try {
            ClaimPhoto photo = photoRepository.findByUuid(photoUuid);
            storageService.deleteFile(claimUuid, photo.getPath());
            photoRepository.delete(photo);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClaimPhotoDTO>> getClaimPhotos(@PathVariable String claimUuid) {
        List<ClaimPhoto> photos = photoRepository.findByClaimId(claimRepository.findByUuid(claimUuid).getId());
        return ResponseEntity.ok(claimPhotoMapper.ToDtoMapper(photos));
    }

    @GetMapping("/{photoUuid}")
    public ResponseEntity<PhotoDTO> getClaimPhotoById(@PathVariable String photoUuid) {

        try {
            ClaimPhoto photo;
            try {
                photo = photoRepository.findByUuid(photoUuid);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
            byte[] imageBytes = Files.readAllBytes(Paths.get(photo.getPath()));
            String base64 = Base64.getEncoder().encodeToString(imageBytes);

            PhotoDTO claimPhotoDTO = new PhotoDTO(
                    photo.getId(), base64
            );

            return ResponseEntity.ok().body(claimPhotoDTO);

        } catch (IOException | java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }
}