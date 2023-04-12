package yt.company.carwash.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Review;
import yt.company.carwash.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final WebClientService webClientService;

    public Review getReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }

    public Page<Review> getAllReviews(PageRequest pageRequest) {
        return reviewRepository.findAll(pageRequest);
    }

    public Page<Review> getCompanyReviews(Long companyId, PageRequest pageRequest) {
        List<Review> compReviews = new ArrayList<>();
        for (Review r : reviewRepository.findAll(pageRequest)) {
            if (r.getCompany().getId() == companyId) {
                compReviews.add(r);
            }
        }
        return new PageImpl<>(compReviews);
    }

    /*public List<Review> getCompanyReviewsByPage(Long companyId, PageRequest pageRequest) {
        List<Review> reviewList = getCompanyReviews(companyId);
        Page<Review> page =
    }*/
    public Review createReview(Review review) {
        review.setDate(new Date());
        return reviewRepository.save(review);
    }

    public void updateReview(Long reviewId, String review) {
        Review r = getReview(reviewId);
        if (review.isEmpty()) {
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
