package org.project.movieapi.Services.Impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.project.movieapi.DTOs.Requests.MovieRequestDto;
import org.project.movieapi.DTOs.Responses.MovieResponseDto;
import org.project.movieapi.Entites.Movie;
import org.project.movieapi.Mappers.MovieMapper;
import org.project.movieapi.Repositories.MovieRepository;
import org.project.movieapi.Services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public Page<MovieResponseDto> getAllMovies(int pageNumber) {
        PageRequest page = PageRequest.of(pageNumber-1,18);
        Page<Movie> moviePage = movieRepository.findAll(page);
        return moviePage.map(movieMapper::toMovieResponseDto);
    }

    @Override
    public void addMovie(MovieRequestDto movieRequestDto) {
        if(movieRepository.existsByImdbId(movieRequestDto.getImdbId())){
            throw new EntityExistsException("Movie already exists");
        }
        Movie movie = movieMapper.toEntity(movieRequestDto);
        movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(Long movieId) {
         return movieRepository.findById(movieId)
                .orElseThrow(()-> new EntityNotFoundException("Movie not found with id "+ movieId));

    }

    @Override
    public Movie findByImdbId(String imdbId){
        return movieRepository.findByImdbId(imdbId).
                orElseThrow(()->new EntityNotFoundException("Movie not found with imdbId "+ imdbId));
    }

    @Override
    public void deleteMovie(Long movieId){
        Movie existingMovie=getMovieById(movieId);
        movieRepository.delete(existingMovie);
    }


    @Override
    public void batchAddMovies(List<MovieRequestDto> movies ) {
        for (var movie : movies){
            movieRepository.existsByImdbId(movie.getImdbId());
            addMovie(movie);
        }
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }


    @Override
    public void batchDeleteMovies(List<Long> ids) {
        for( Long id : ids){
            deleteMovie(id);
        }
    }







}
