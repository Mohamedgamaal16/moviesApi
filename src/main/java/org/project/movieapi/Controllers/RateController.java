package org.project.movieapi.Controllers;

import lombok.RequiredArgsConstructor;
import org.project.movieapi.DTOs.Requests.RateRequestDto;
import org.project.movieapi.Services.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie/rate")
@RequiredArgsConstructor
public class RateController {
    private final RateService rateService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> rateMovie(@RequestBody RateRequestDto request, Authentication authentication) {
        String username = authentication.getName();
        rateService.rateMovie(username, request);
        return ResponseEntity.ok("Rated successfully");
    }
}
