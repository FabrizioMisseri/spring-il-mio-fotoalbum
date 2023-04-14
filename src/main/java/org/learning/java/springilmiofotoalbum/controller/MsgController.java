package org.learning.java.springilmiofotoalbum.controller;

import org.learning.java.springilmiofotoalbum.exceptions.MsgNotFoundException;
import org.learning.java.springilmiofotoalbum.model.Msg;
import org.learning.java.springilmiofotoalbum.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/msgs")
public class MsgController {

    @Autowired
    MsgService msgService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Msg> msgs;
        if (keyword.isEmpty()) {
            msgs = msgService.getAllMsgs();
        } else {
            msgs = msgService.getFilteredMsgs(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("msgs", msgs);

        return "/msgs/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Msg msg = msgService.getById(id);
            model.addAttribute("msg", msg);
            return "/msgs/show";
        } catch (MsgNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L' email con " + id + " non è stata trovata");
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = msgService.deleteById(id);
            if (success) {
                redirectAttributes.addFlashAttribute("message", "la cancellazione è andata a buon fine");
            } else {
                redirectAttributes.addFlashAttribute("message", "la cancellazione non è andata a buon fine");
            }
        } catch (MsgNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L' email con " + id + " non è stata trovata");
        }
        return "redirect:/msgs";
    }
}
