package org.michaelbae.recipeplanner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String uploadDir;

    @Autowired
    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            // Normalize file name and generate unique name
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + fileExtension;

            // Copy file to target location
            Path targetLocation = Paths.get(uploadDir).resolve(newFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return the relative path for storage in the database
            return "/assets/imgs/uploads/" + newFilename;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file, ERROR: ", ex);
        }
    }
}