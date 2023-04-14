package org.learning.java.springilmiofotoalbum.repository;

import org.learning.java.springilmiofotoalbum.model.Msg;
import org.learning.java.springilmiofotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MsgRepository extends JpaRepository<Msg, Integer> {
}
