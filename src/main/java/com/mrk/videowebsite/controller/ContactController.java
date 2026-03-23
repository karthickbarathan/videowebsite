package com.mrk.videowebsite.controller;


import org.springframework.web.bind.annotation.*;
import com.mrk.videowebsite.service.ContactService;
import com.mrk.videowebsite.model.Contact;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin("*")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service){
        this.service = service;
    }

    @PostMapping
    public Contact sendMessage(@RequestBody Contact contact){
        return service.saveMessage(contact);
    }

    @GetMapping
    public List<Contact> getAllMessages(){
        return service.getAllMessages();
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable String id){
        service.deleteMessage(id);
        return "Message deleted";
    }
}
