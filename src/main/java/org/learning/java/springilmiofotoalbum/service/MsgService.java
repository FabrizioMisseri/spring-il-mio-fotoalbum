package org.learning.java.springilmiofotoalbum.service;

import jakarta.validation.Valid;
import org.learning.java.springilmiofotoalbum.exceptions.MsgNotFoundException;
import org.learning.java.springilmiofotoalbum.exceptions.PhotoNotFoundException;
import org.learning.java.springilmiofotoalbum.model.Msg;
import org.learning.java.springilmiofotoalbum.repository.MsgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Msg> getAllMsgs() {
        return msgRepository.findAll();
    }

    public List<Msg> getFilteredMsgs(String keyword) {
        return msgRepository.findByEmailContainingIgnoreCase(keyword);
    }

    public Msg getById(Integer id) {
        Optional<Msg> result = msgRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new MsgNotFoundException("" + id);
        }
    }

    public boolean deleteById(Integer id) {
        msgRepository.findById(id).orElseThrow(() -> new MsgNotFoundException("" + id));
        try {
            msgRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
