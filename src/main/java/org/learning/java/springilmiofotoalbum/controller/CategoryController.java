package org.learning.java.springilmiofotoalbum.controller;

import jakarta.validation.Valid;
import org.learning.java.springilmiofotoalbum.model.Category;
import org.learning.java.springilmiofotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "categories/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Category category = categoryService.getById(id);
            //
            model.addAttribute("category", category);
            return "/categories/edit";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/categories/create";
        }
        categoryService.createCategory(formCategory);
        return "redirect:/categories";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("category") Category formCategory) {
        categoryService.updateCategory(formCategory, id);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = categoryService.deleteById(id);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "la cancellazione è andata a buon fine");
                return "redirect:/categories";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
