package com.example.ClaimManagementSystem.service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

public interface FileStorageService {

    String storeFile(MultipartFile file, Long claimId) throws IOException;
    Path loadFile(Long claimId, String filename);
    void deleteFile(Long claimId, String filename) throws IOException;
}