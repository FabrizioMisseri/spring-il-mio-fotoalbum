package org.learning.java.springilmiofotoalbum.service;

import org.learning.java.springilmiofotoalbum.model.Photo;
import org.learning.java.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Photo getById(Integer id) {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException();
        }
    }
}
