package org.learning.java.springilmiofotoalbum.api;

import org.learning.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.learning.java.springilmiofotoalbum.model.Photo;
import org.learning.java.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoRestController {

    @Autowired
    PhotoService photoService;

    @GetMapping
    public List<Photo> photoList(@RequestParam(name = "q") Optional<String> search) {

        if (search.isPresent()) {
            return photoService.getFilteredPhotosApi(search.get());
        }
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public Photo getById(@PathVariable Integer id) {

        try {
            return photoService.getById(id);
        } catch (PhotoNotFoundException e) {
            new ResponseStatusException(HttpStatus.NOT_FOUND, "La foto con " + id + " non è stata trovata");
            return null;
        }
    }

}
