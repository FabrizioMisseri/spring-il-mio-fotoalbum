package org.learning.java.springilmiofotoalbum.repository;

import org.learning.java.springilmiofotoalbum.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}