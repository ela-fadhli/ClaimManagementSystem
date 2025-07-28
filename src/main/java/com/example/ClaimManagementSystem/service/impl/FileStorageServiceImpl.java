package com.example.ClaimManagementSystem.service.impl;

import com.example.ClaimManagementSystem.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path rootLocation;

    public FileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) throws IOException {
        this.rootLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(this.rootLocation);
    }

    @Override
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

    @Override
    public Path loadFile(Long claimId, String filename) {
        return rootLocation.resolve(claimId.toString()).resolve(filename);
    }

    @Override
    public void deleteFile(Long claimId, String filename) throws IOException {
        Path filePath = loadFile(claimId, filename);
        Files.deleteIfExists(filePath);
    }
}
