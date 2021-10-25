package io.javabrains.moviecatalogservice.resources;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.UserRating;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class MovieCatalogResource {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private final DiscoveryClient discoveryClient;

    @GetMapping("{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // Get all rates movie ids
        UserRating userRating = restTemplate.getForObject(
                "http://rating-data-service/api/rating/user/" + userId,
                UserRating.class);

        // For each movie id call movie info service and get details

        // Collect all the details
        return userRating.getRatings().stream()
                .map(
                        rating -> {
                            Movie movie = restTemplate.getForObject(
                                    "http://movie-info-service/api/movie/" + rating.getMovieId(),
                                    Movie.class);

                            //Movie movie = webClientBuilder
                            //        .build()
                            //        .get()
                            //        .uri( "http://localhost:8082/api/movie/" + rating.getMovieId())
                            //        .retrieve()
                            //        .bodyToMono(Movie.class)
                            //        .block();

                            return CatalogItem.builder()
                                    .rating(rating.getRating())
                                    .name(movie.getName())
                                    .desc("Aliens")
                                    .build();
                        }
                )
                .collect(Collectors.toList());

    }

}
