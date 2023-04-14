package org.learning.java.springilmiofotoalbum.service;

import jakarta.validation.Valid;
import org.learning.java.springilmiofotoalbum.model.Msg;
import org.learning.java.springilmiofotoalbum.repository.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgService {

    @Autowired
    MsgRepository msgRepository;

    public Msg createMsg(@Valid Msg msg) {
        Msg msgToPersist = new Msg();
        msgToPersist.setEmail(msg.getEmail());
        msgToPersist.setMessage(msg.getMessage());
        return msgRepository.save(msgToPersist);
    }
}
