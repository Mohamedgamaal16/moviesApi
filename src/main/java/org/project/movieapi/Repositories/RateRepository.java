package org.project.movieapi.Repositories;

import org.project.movieapi.Entites.Movie;
import org.project.movieapi.Entites.Rate;
import org.project.movieapi.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByUserAndMovie(User user, Movie movie);

}
