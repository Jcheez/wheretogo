package com.wheretogo.backend.service;

import com.wheretogo.backend.models.Photo;
import com.wheretogo.backend.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes())
        );
        photo = this.photoRepository.insert(photo);
        return photo.getId();
    }

    public Photo getPhotoById(String id) {
        return this.photoRepository.findById(id).orElseThrow(() -> new RuntimeException(
                String.format("There is no photo found with the id %s", id)
        ));
    }
}
