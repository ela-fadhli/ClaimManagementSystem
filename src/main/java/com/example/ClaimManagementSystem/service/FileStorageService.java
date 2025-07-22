package com.example.ClaimManagementSystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path rootLocation;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) throws IOException {
        this.rootLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.rootLocation);
    }

    public String storeFile(MultipartFile file, Long claimId) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = uuid + extension;

        Path claimDir = this.rootLocation.resolve(claimId.toString());
        Files.createDirectories(claimDir);

        Path targetPath = claimDir.resolve(filename);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

    public Path loadFile(Long claimId, String filename) {
        return rootLocation.resolve(claimId.toString()).resolve(filename);
    }

    public void deleteFile(Long claimId, String filename) throws IOException {
        Path filePath = loadFile(claimId, filename);
        Files.deleteIfExists(filePath);
    }
}