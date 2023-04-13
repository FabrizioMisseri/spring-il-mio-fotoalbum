package org.learning.java.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.learning.java.springilmiofotoalbum.model.Category;
import org.learning.java.springilmiofotoalbum.model.Photo;
import org.learning.java.springilmiofotoalbum.service.CategoryService;
import org.learning.java.springilmiofotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            return "/photos/show";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        //
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        //
        model.addAttribute("photo", new Photo());
        return "/photos/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/photos/create";
        }
        photoService.createPhoto(formPhoto);
        return "redirect:/photos";
    }

    //
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Photo photo = photoService.getById(id);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("photo", photo);
        return "/photos/edit";
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/photos/edit";
        }
        photoService.update(id, formPhoto);
        return "redirect:/photos/{id}";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = photoService.deleteById(id);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "la cancellazione è andata a buon fine");
                return "redirect:/photos";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
