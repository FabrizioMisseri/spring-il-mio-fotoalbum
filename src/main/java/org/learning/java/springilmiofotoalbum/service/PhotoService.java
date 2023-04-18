package org.learning.java.springilmiofotoalbum.service;

import org.learning.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.learning.java.springilmiofotoalbum.model.Image;
import org.learning.java.springilmiofotoalbum.model.ImageForm;
import org.learning.java.springilmiofotoalbum.model.Photo;
import org.learning.java.springilmiofotoalbum.repository.ImageRepository;
import org.learning.java.springilmiofotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ImageRepository imageRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public List<Photo> getFilteredPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }

    // get all-photos API
    public List<Photo> getFilteredPhotosApi(String keyword) {
        List<Photo> photos = getFilteredPhotos(keyword);
        List<Photo> filteredPhotos = new ArrayList<>();
        // id, title, description
        for (Photo p : photos) {
            Photo copyP = new Photo();
            copyP.setId(p.getId());
            copyP.setTitle(p.getTitle());
            copyP.setDescription(p.getDescription());
            copyP.setVisible(p.getVisible());
            copyP.setPhotoView(p.getPhotoView());
            filteredPhotos.add(copyP);
        }
        return filteredPhotos;
    }

    public Photo getById(Integer id) {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException("" + id);
        }
    }

    public Photo createPhoto(Photo formPhoto) {
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setUrl(formPhoto.getUrl());
        photoToPersist.setVisible(formPhoto.getVisible());
        photoToPersist.setCategories(formPhoto.getCategories());
        return photoRepository.save(photoToPersist);
    }

    //

    public Photo update(Integer id, Photo formPhoto) {
        Photo photoToUpdate = getById(id);
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setUrl(formPhoto.getUrl());
        photoToUpdate.setVisible(formPhoto.getVisible());
        photoToUpdate.setCategories(formPhoto.getCategories());
        return photoRepository.save(photoToUpdate);
    }

    public boolean deleteById(Integer id) {
        photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("" + id));
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // COVER - IMAGE
    public Image updateCover(Integer id, ImageForm imageForm)
            throws PhotoNotFoundException, IOException {
        Photo photo = getById(id);
        photo.setPhotoView(true);
        // IF image exist -> delete
        Image oldImage = photo.getCover();
        if (oldImage != null) {
            // detach
            photo.setCover(null);
            oldImage.setPhoto(null);
            imageRepository.delete(oldImage);
        }
        Image newImage = new Image();
        newImage.setPhoto(photo);
        newImage.setContent(imageForm.getMultipartFile().getBytes());
        return imageRepository.save(newImage);
    }

}
