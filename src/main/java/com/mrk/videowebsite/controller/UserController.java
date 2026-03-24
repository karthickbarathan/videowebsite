package com.mrk.videowebsite.controller;

import com.mrk.videowebsite.dto.LoginRequest;
import com.mrk.videowebsite.repository.UserRepository;
import com.mrk.videowebsite.security.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.mrk.videowebsite.service.UserService;
import com.mrk.videowebsite.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService service;
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final JwtService jwtService;


    @PostMapping("/register")
    public User register(@RequestBody User user){
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.status(401).body("Invalid password");
        }

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(Map.of("token",token));
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/me")
    public Map<String, String> getCurrentUser(Authentication auth) {
        Map<String, String> user = new HashMap<>();
        user.put("email", auth.getName());
        user.put("role", auth.getAuthorities().iterator().next().getAuthority());
        return user;
    }
}