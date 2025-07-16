package org.project.movieapi.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private final Date timeStamp;
}
