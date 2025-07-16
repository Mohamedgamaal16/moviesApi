//package org.project.movieapi.util;
//
//import lombok.RequiredArgsConstructor;
//import org.project.movieapi.Entites.Enums.Role;
//import org.project.movieapi.Entites.User;
//import org.project.movieapi.Repositories.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CommandLineStartupRunner implements CommandLineRunner {
//    private final UserRepository userRepo;
//    private final PasswordEncoder encoder;
//
//    @Override
//    public void run(String... args) {
//        if (userRepo.existsByUsername("hassan@email.com")) {
//            return;
//        }
//        User admin = User.builder()
//                .role(Role.ADMIN)
//                .username("hassan")
//                .email("hassan@email.com")
//                .password(encoder.encode("123456789"))
//                .build();
//        userRepo.save(admin);
//    }
//}