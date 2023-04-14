package org.learning.java.springilmiofotoalbum.repository;

import org.learning.java.springilmiofotoalbum.model.Msg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MsgRepository extends JpaRepository<Msg, Integer> {
    public List<Msg> findByEmailContainingIgnoreCase(String name);
}
