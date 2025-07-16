package org.project.movieapi.DTOs.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.project.movieapi.Entites.Movie;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OMDbResponse {

    @JsonProperty("Search")
    private List<MovieSearchResponseDto> movies;
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String response;
}
