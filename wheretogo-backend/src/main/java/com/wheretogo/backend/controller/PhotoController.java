package com.wheretogo.backend.controller;

import com.wheretogo.backend.models.Photo;
import com.wheretogo.backend.service.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public ResponseEntity<String> addPhoto(@RequestParam String title, @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(this.photoService.addPhoto(title, image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable String id) {
        return ResponseEntity.ok(this.photoService.getPhotoById(id));
    }

}
