package org.project.movieapi.Services.Impl;

import lombok.RequiredArgsConstructor;
import org.project.movieapi.DTOs.Requests.RateRequestDto;
import org.project.movieapi.Entites.Movie;
import org.project.movieapi.Entites.Rate;
import org.project.movieapi.Entites.User;
import org.project.movieapi.Repositories.RateRepository;
import org.project.movieapi.Services.MovieService;
import org.project.movieapi.Services.RateService;
import org.project.movieapi.Services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final MovieService movieService;
    private final UserService userService;

    @Override
    public void rateMovie(String username,RateRequestDto rateRequest) {

        User user = userService.findByUsername(username);
        Movie movie = movieService.findByImdbId(rateRequest.getImdbId());
        Rate rate = rateRepository.findByUserAndMovie(user, movie).orElse(new Rate());

        rate.setUser(user);
        rate.setMovie(movie);
        rate.setRate(rateRequest.getRate());

        rateRepository.save(rate);
        updateMovieAverageRating(movie);
    }

    @Override
    public void updateMovieAverageRating(Movie movie) {
        List<Rate> rates = rateRepository.findAll()
                .stream()
                .filter(r -> r.getMovie().equals(movie))
                .toList();

        float average = (float) rates.stream().mapToDouble(Rate::getRate).average().orElse(0);
        movie.setAverageRate(average);
        movieService.saveMovie(movie);
    }

}


