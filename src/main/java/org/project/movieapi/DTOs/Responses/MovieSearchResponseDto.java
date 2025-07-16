package org.project.movieapi.DTOs.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MovieSearchResponseDto {
    private String imdbID;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Poster")
    private String poster;
}
