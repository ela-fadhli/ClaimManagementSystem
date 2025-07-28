package com.example.ClaimManagementSystem.service;

import com.example.ClaimManagementSystem.model.ClaimPhoto;
import com.example.ClaimManagementSystem.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public interface ClaimPhotoStorageService {

    ClaimPhoto storeFile(MultipartFile file, String claimId) throws IOException;
    Path loadFile(Long claimId, String filename);
    void deleteFile(String claimUuid, String filename) throws IOException;
}