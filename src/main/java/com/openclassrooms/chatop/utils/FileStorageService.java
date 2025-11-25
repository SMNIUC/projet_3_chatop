package com.openclassrooms.chatop.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final String uploadDir = "uploads/rentals/";

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!");
        }
    }

    public String saveFile(MultipartFile file) {
        try {
            // Get original filename and extension
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : ".jpg";

            // Generate unique filename
            String filename = UUID.randomUUID() + extension;

            // Save file
            Path filePath = Paths.get(uploadDir + filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return URL path that will be stored in database
            return "/api/rentals/images/" + filename;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
