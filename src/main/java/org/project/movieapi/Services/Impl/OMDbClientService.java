package org.project.movieapi.Services.Impl;
import lombok.extern.slf4j.Slf4j;
import org.project.movieapi.DTOs.Responses.MovieResponseDto;
import org.project.movieapi.DTOs.Responses.OMDbResponse;
import org.project.movieapi.Exceptions.MovieApiException;
import org.project.movieapi.Mappers.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class OMDbClientService {

    private final RestTemplate restTemplate;

    private final String apiKey;

    @Value("${omdb.api.url}")
    private  String OMDbUrl;

    @Autowired
    private MovieMapper movieMapper;


    public OMDbClientService(RestTemplate restTemplate, @Value("${omdb.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public OMDbResponse searchMovie(String searchTerm){
        try {
            String url = buildSearchUrl(searchTerm);
            System.out.println(url);
            return restTemplate.getForObject(url, OMDbResponse.class);
        }catch (RestClientException e){
            log.error("Error searching movie with term {}: {}", searchTerm, e.getMessage());
            throw new MovieApiException("Failed to search movies", e);
        }


    }


    private String buildSearchUrl(String searchTerm) {
        return UriComponentsBuilder
                .fromPath("/")
                .queryParam("apikey", apiKey)
                .queryParam("s", searchTerm)
                .build()
                .toUriString();
    }



    public MovieResponseDto getMovieByIdFromOmdbApi(String imdbID) {
        String url = OMDbUrl+"?i=" + imdbID + "&"+"apikey="+apiKey;
        ResponseEntity<MovieResponseDto> response = restTemplate.getForEntity(url, MovieResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            MovieResponseDto movieResponse = response.getBody();
            return movieResponse;
        } else {
            throw new RuntimeException("Movie not found in OMDb API");
        }
    }


}
