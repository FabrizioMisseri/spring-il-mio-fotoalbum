package org.learning.java.springilmiofotoalbum.controller;

import org.learning.java.springilmiofotoalbum.model.Image;
import org.learning.java.springilmiofotoalbum.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    ImageRepository imageRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer id) {
        Image img = imageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND));
        MediaType mediaType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(img.getContent());
    }
}