package org.project.movieapi.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.project.movieapi.DTOs.Requests.MovieRequestDto;
import org.project.movieapi.DTOs.Responses.MovieResponseDto;
import org.project.movieapi.Entites.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper{

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "runtime", target = "runtime")
    Movie toEntity(MovieRequestDto movieRequestDto);

    MovieResponseDto toMovieResponseDto(Movie movie);



}
