package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;

public interface ClaimPhotoStorageService {

    ClaimPhoto storeFile(MultipartFile file, String claimId) throws IOException;
    Path loadFile(Long claimId, String filename);
    void deleteFile(String claimUuid, String filename) throws IOException;
}