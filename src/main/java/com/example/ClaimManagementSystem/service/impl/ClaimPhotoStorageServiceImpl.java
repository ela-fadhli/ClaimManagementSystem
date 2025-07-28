package com.example.ClaimManagementSystem.service.impl;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import com.example.ClaimManagementSystem.service.ClaimPhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class ClaimPhotoStorageServiceImpl implements ClaimPhotoStorageService {

    private final Path rootLocation;
    private final ClaimRepository claimRepository;

    public ClaimPhotoStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir, ClaimRepository claimRepository) throws IOException {
        this.rootLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.rootLocation);
        this.claimRepository = claimRepository;
    }

    @Override
    public ClaimPhoto storeFile(MultipartFile file, String claimId) throws IOException {
        // Create claim-specific directory
        Path claimDir = this.rootLocation.resolve(claimId);
        Files.createDirectories(claimDir);

        // Generate unique filename
        String uuid = UUID.randomUUID().toString();
        String extension = Objects.requireNonNull(file.getOriginalFilename()).substring(
                file.getOriginalFilename().lastIndexOf(".")
        );
        String filename = uuid + extension;

        // Store the file
        Path targetPath = claimDir.resolve(filename);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        // Create and return ClaimPhoto entity
        ClaimPhoto photo = new ClaimPhoto();
        photo.setUuid(uuid);
        photo.setPath(targetPath + "");
        photo.setOriginalFileName(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setClaimId(claimRepository.findByUuid(claimId).getId());
        photo.setCreatedAt(new Date());

        return photo;
    }

    @Override
    public Path loadFile(Long claimId, String filename) {
        return rootLocation.resolve(claimId.toString()).resolve(filename);
    }

    @Override
    public void deleteFile(String claimUuid, String filename) throws IOException {
        Path filePath = loadFile(claimRepository.findByUuid(claimUuid).getId(), filename);
        Files.deleteIfExists(filePath);
    }
}
