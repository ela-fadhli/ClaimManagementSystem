package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class ClaimPhotoStorageService {

    private final Path rootLocation;

    public ClaimPhotoStorageService(@Value("${file.upload-dir}") String uploadDir) throws IOException {
        this.rootLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.rootLocation);
    }

    public ClaimPhoto storeFile(MultipartFile file, Long claimId) throws IOException {
        // Create claim-specific directory
        Path claimDir = this.rootLocation.resolve(claimId.toString());
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
        photo.setPath(targetPath + "");  // Stores just the filename, not full path
        photo.setOriginalFileName(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setClaimId(claimId);
        photo.setCreatedAt(new Date());

        return photo;
    }

    public Path loadFile(Long claimId, String filename) {
        return rootLocation.resolve(claimId.toString()).resolve(filename);
    }

    public void deleteFile(Long claimId, String filename) throws IOException {
        Path filePath = loadFile(claimId, filename);
        Files.deleteIfExists(filePath);
    }
}