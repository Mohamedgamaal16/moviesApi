package org.project.movieapi.Services;


import org.project.movieapi.DTOs.Requests.MovieRequestDto;
import org.project.movieapi.DTOs.Responses.MovieResponseDto;
import org.project.movieapi.Entites.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MovieService {

    Page<MovieResponseDto> getAllMovies(int pageNumber);
    void addMovie(MovieRequestDto movieRequestDto);
    Movie getMovieById(Long id);
    Movie findByImdbId(String imdbId);
    void deleteMovie(Long id);
    void batchDeleteMovies(List<Long>ids);
    void batchAddMovies(List<MovieRequestDto> imdbIds);
    void saveMovie(Movie movie);


}
