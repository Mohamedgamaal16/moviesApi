package org.project.movieapi.DTOs.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MovieResponseDto {
    private Long id;
    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Runtime")
    private String runtime;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Language")
    private String language;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("imdbRating")
    private String imdbRating;
}
