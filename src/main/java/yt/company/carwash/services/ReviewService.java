package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Review;
import yt.company.carwash.repository.ReviewRepository;
import yt.company.carwash.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ClientService clientService;
    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Review not found"));
    }
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    public List<Review> getCompanyReviews(Long companyId) {
        List<Review> compReviews = new ArrayList<>();
        for(Review r : getAllReviews()) {
            if(r.getCompany().getId() == companyId) {
                compReviews.add(r);
            }
        }
        return compReviews;
    }
    public Review createReview(Review review) {
        review.setDate(new Date());
        return reviewRepository.save(review);
    }
    public void updateReview(Long reviewId, String review) {
        Review r = getReview(reviewId);
        if(review.isEmpty()) {
            throw new RuntimeException("Review value can not take an empty String");
        }
        r.setReview(review);
        r.setDate(new Date());
        reviewRepository.save(r);
    }
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
