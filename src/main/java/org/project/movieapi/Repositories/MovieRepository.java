package org.project.movieapi.Repositories;

import org.project.movieapi.Entites.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findByTitleContainingIgnoreCase(String title,Pageable pageable);

    Optional<Movie> findByImdbId(String imdbId);

    boolean existsByImdbId(String ImdbId);




}
