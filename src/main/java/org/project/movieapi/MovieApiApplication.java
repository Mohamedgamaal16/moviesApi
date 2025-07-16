package org.project.movieapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableJpaRepositories
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class MovieApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(MovieApiApplication.class, args);

    }

}
