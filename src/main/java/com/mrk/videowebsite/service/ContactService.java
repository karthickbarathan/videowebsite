package com.mrk.videowebsite.service;


import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.mrk.videowebsite.repository.ContactRepository;
import com.mrk.videowebsite.model.Contact;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository){
        this.repository = repository;
    }

    public Contact saveMessage(Contact contact){
        return repository.save(contact);
    }

    public List<Contact> getAllMessages(){
        return repository.findAll();
    }

    public void deleteMessage(String id){
        repository.deleteById(id);
    }
}