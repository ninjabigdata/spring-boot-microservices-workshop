package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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

    @GetMapping("user/{userId}")
    public UserRating getRatingByUser(@PathVariable("userId") String userId) {
        return new UserRating(Arrays.asList(
                Rating.builder()
                        .movieId("1")
                        .rating(4)
                        .build(),
                Rating.builder()
                        .movieId("2")
                        .rating(5)
                        .build())
        );
    }

}
