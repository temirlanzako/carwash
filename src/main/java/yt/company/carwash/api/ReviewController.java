package yt.company.carwash.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yt.company.carwash.models.Review;
import yt.company.carwash.services.ReviewService;

@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getReview(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reviewService.getReview(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping(value = "/company/{companyId}")
    public ResponseEntity<Object> getCompanyReviews(@PathVariable(name = "companyId") Long id) {
        return ResponseEntity.ok(reviewService.getCompanyReviews(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.createReview(review), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{reviewId}")
    public ResponseEntity<?> updateReview(@RequestParam String review,
                                          @PathVariable Long reviewId) {
        try {
            reviewService.updateReview(reviewId, review);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
