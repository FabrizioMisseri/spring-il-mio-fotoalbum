package org.learning.java.springilmiofotoalbum.service;

import org.learning.java.springilmiofotoalbum.exceptions.CategoryNotFoundException;
import org.learning.java.springilmiofotoalbum.model.Category;
import org.learning.java.springilmiofotoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("" + id));
    }


    public Category createCategory(Category formCategory) {
        Category categoryToPersist = new Category();
        categoryToPersist.setName(formCategory.getName());
        return categoryRepository.save(categoryToPersist);
    }

    public Category updateCategory(Category formCategory, Integer id) {
        Category categoryToUpdate = getById(id);
        categoryToUpdate.setName(formCategory.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    public boolean deleteById(Integer id) {
        getById(id);
        try {
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
