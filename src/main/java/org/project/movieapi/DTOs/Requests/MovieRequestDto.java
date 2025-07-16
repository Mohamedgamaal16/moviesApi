package org.project.movieapi.DTOs.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MovieRequestDto {
    private Long id;

    @JsonProperty("imdbID")
    @NotBlank(message = "imdbID is required")
    private String imdbId;

    @JsonProperty("Title")
    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    @JsonProperty("Type")
    @NotBlank(message = "Type is required")
    private String type;

    @JsonProperty("Plot")
    @Size(max = 2000)
    private String plot;

    @JsonProperty("Released")
    @NotBlank(message = "Released date is required")
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
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Poster must be a valid URL"
    )
    private String poster;

    @JsonProperty("imdbRating")
    @Pattern(
            regexp = "^[0-9](\\.\\d{1,2})?$",
            message = "imdbRating must be a valid rating (e.g., 7.5)"
    )
    private String imdbRating;
}
