package com.example.studentmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private static final List<String> ALLOWED_TYPES = List.of("image/jpeg", "image/png", "image/jpg", "image/webp");
    private static final long MAX_SIZE_BYTES = 2L * 1024 * 1024; // 2 MB
    private static final String UPLOAD_DIR = "uploads/students";

    @PostMapping("/student-photo")
    public ResponseEntity<?> uploadStudentPhoto(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("error", "No file selected");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            response.put("error", "Only JPG, PNG and WEBP images are allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        if (file.getSize() > MAX_SIZE_BYTES) {
            response.put("error", "File size must not exceed 2MB");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalName = file.getOriginalFilename() == null ? "photo" : file.getOriginalFilename();
            String extension = "";
            int dot = originalName.lastIndexOf('.');
            if (dot >= 0) {
                extension = originalName.substring(dot);
            }
            // Sanitize: always generate a random filename server-side, never
            // trust the client-supplied name/path to avoid path traversal.
            String safeFilename = UUID.randomUUID() + extension.replaceAll("[^a-zA-Z0-9.]", "");

            Path target = uploadPath.resolve(safeFilename).normalize();
            if (!target.startsWith(uploadPath.normalize())) {
                response.put("error", "Invalid file path");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            response.put("url", "/uploads/students/" + safeFilename);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("error", "Failed to store file");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
