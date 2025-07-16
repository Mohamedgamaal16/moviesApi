package org.project.movieapi.Entites;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imdbId;
    private String title;
    private String type;
    private String plot;
    private String released;
    private String rated;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String writer;
    private String language;
    private String country;
    private String poster;
    private String imdbRating;

    private float averageRate;


    @OneToMany(mappedBy = "movie")
    private List<Rate> rates ;



}
