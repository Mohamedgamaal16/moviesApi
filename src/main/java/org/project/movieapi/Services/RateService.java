package org.project.movieapi.Services;

import org.project.movieapi.DTOs.Requests.RateRequestDto;
import org.project.movieapi.Entites.Movie;
import org.project.movieapi.Entites.Rate;

public interface RateService {
    void rateMovie(String username, RateRequestDto rate);
    void updateMovieAverageRating(Movie movie);
}

