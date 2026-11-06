package com.parkgrid.auth.service;

import com.parkgrid.auth.entity.AppUser;
import com.parkgrid.auth.repo.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean CommandLineRunner seed(AppUserRepository repo, PasswordEncoder encoder){
        return args -> {
            if(!repo.existsByUsername("user1")) repo.save(new AppUser("user1", encoder.encode("User@123"), "USER"));
            if(!repo.existsByUsername("admin")) repo.save(new AppUser("admin", encoder.encode("Admin@123"), "ADMIN"));
        };
    }
}
