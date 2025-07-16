package org.project.movieapi.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.movieapi.DTOs.Requests.MovieRequestDto;
import org.project.movieapi.DTOs.Responses.MovieResponseDto;
import org.project.movieapi.Entites.Movie;
import org.project.movieapi.Mappers.MovieMapper;
import org.project.movieapi.Services.Impl.OMDbClientService;
import org.project.movieapi.Services.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final OMDbClientService omDbClientService;

    @GetMapping("/{pageNumber}/all")
    public ResponseEntity<Page<MovieResponseDto>> getAllMovies(@PathVariable int pageNumber){
        Page<MovieResponseDto> movies = movieService.getAllMovies(pageNumber);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable Long id){
        Movie movie = movieService.getMovieById(id);
        MovieResponseDto responseMovie = movieMapper.toMovieResponseDto(movie);
        return new ResponseEntity<>(responseMovie, HttpStatus.OK);
    }

    @GetMapping("/omdb/{imdbId}")
    public ResponseEntity<MovieResponseDto> getMovieByImdbId(@PathVariable String imdbId){
        MovieResponseDto movie = omDbClientService.getMovieByIdFromOmdbApi(imdbId);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> addMovie(@Valid @RequestBody MovieRequestDto movieRequestDto){
        movieService.addMovie(movieRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/batch-add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public  ResponseEntity<Map<String, String >> addMovieBatches(@Valid @RequestBody List<MovieRequestDto> movies){
        movieService.batchAddMovies(movies);
        return ResponseEntity.ok(Map.of("message","Movies added successfully"));
    }


    @PostMapping("/batch-delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteMovieBatches(@Valid @RequestBody List<Long> ids){
        movieService.batchDeleteMovies(ids);
        return ResponseEntity.noContent().build();
    }



}
