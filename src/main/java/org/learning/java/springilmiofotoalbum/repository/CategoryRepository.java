package org.learning.java.springilmiofotoalbum.repository;

import org.learning.java.springilmiofotoalbum.model.Category;
import org.learning.java.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}

