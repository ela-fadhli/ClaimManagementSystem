package com.example.ClaimManagementSystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

public interface FileStorageService {

    String storeFile(MultipartFile file, Long claimId) throws IOException;
    Path loadFile(Long claimId, String filename);
    void deleteFile(Long claimId, String filename) throws IOException;
}