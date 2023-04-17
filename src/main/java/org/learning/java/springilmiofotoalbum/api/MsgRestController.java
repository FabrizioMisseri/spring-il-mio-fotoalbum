package org.learning.java.springilmiofotoalbum.api;

import jakarta.validation.Valid;
import org.learning.java.springilmiofotoalbum.model.Msg;
import org.learning.java.springilmiofotoalbum.model.Photo;
import org.learning.java.springilmiofotoalbum.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/msgs")
public class MsgRestController {

    @Autowired
    MsgService msgService;

    @PostMapping
    public Msg create(@Valid @RequestBody Msg msg) {
        return msgService.createMsg(msg);
    }
}
