package org.learning.java.springilmiofotoalbum.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

    private MultipartFile multipartFile;

    private Photo photo;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}