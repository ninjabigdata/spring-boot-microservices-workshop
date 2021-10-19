package io.javabrains.movieinfoservice.resources;

import io.javabrains.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movie")
public class MovieResource {

    @GetMapping("{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        return Movie.builder()
                .movieId(movieId)
                .name("Transformers")
                .build();
    }

}
