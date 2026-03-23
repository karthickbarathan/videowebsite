package com.mrk.videowebsite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.mrk.videowebsite.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
