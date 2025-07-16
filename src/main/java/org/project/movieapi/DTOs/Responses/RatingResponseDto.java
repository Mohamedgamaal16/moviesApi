package org.project.movieapi.DTOs.Responses;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RatingResponseDto {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;
}
