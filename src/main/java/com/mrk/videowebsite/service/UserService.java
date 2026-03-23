package com.mrk.videowebsite.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.mrk.videowebsite.repository.UserRepository;
import com.mrk.videowebsite.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
//        user.setCreatedAt(LocalDateTime.now());
//        user.setRole("ADMIN");
//        return repository.save(user);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
