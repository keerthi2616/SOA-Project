package com.parkgrid.auth.controller;

import com.parkgrid.auth.dto.*;
import com.parkgrid.auth.entity.AppUser;
import com.parkgrid.auth.repo.AppUserRepository;
import com.parkgrid.auth.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AppUserRepository repo; private final PasswordEncoder encoder; private final JwtService jwt;
    public AuthController(AppUserRepository repo, PasswordEncoder encoder, JwtService jwt){this.repo=repo;this.encoder=encoder;this.jwt=jwt;}

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request){
        if(repo.existsByUsername(request.username())) return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        repo.save(new AppUser(request.username(), encoder.encode(request.password()), "USER"));
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        AppUser user=repo.findByUsername(request.username()).orElse(null);
        if(user==null || !encoder.matches(request.password(), user.getPassword())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        return ResponseEntity.ok(new LoginResponse(jwt.generate(user.getUsername(), user.getRole()), user.getUsername(), user.getRole()));
    }
}
