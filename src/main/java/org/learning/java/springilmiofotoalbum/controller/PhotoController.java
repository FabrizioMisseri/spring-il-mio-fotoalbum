package org.learning.java.springilmiofotoalbum.controller;

import org.learning.java.springilmiofotoalbum.model.Photo;
import org.learning.java.springilmiofotoalbum.service.CategoryService;
import org.learning.java.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    PhotoService photoService;
    @Autowired
    CategoryService categoryService;


    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Photo> photos;
        if (keyword.isEmpty()) {
            photos = photoService.getAllPhotos();
        } else {
            photos = photoService.getFilteredPhotos(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("photos", photos);

        return "/photos/index";
    }
}
