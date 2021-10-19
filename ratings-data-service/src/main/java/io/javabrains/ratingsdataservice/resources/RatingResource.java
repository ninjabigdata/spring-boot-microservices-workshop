package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rating")
public class RatingResource {

    @GetMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return Rating.builder()
                .movieId(movieId)
                .rating(4)
                .build();
    }

}
