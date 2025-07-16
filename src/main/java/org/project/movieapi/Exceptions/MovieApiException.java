package org.project.movieapi.Exceptions;

public class MovieApiException extends RuntimeException{

    public MovieApiException(){
        super();
    }

    public MovieApiException(String message, Throwable cause){
        super(message, cause);
    }
}
