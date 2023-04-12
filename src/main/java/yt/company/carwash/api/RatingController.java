package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.services.RatingService;

@RestController
@RequestMapping(value = "/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @GetMapping
    public ResponseEntity<?> getAverageRatingOverall() {
        return ResponseEntity.ok(ratingService.getAverageRatingOverall());
    }

    @GetMapping(value = "/{companyId}")
    public ResponseEntity<?> getRating(@PathVariable Long companyId) {
        try {
            return ResponseEntity.ok(ratingService.getCompanyRating(companyId));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteOneRating(@PathVariable Long id) {
        try {
            ratingService.deleteOneRating(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
