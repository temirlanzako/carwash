package yt.company.carwash.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yt.company.carwash.models.Rating;
import yt.company.carwash.repository.RatingRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;

    public Integer getCompanyRating(Long companyId) {
        float sum = 0;
        List<Rating> ratingList = ratingRepository.findGradeByCompany_Id(companyId);
        for(Rating rating : ratingList) {
            sum += rating.getGrade();
        }
        float result = sum / ratingList.size();
        return Math.round(result);
    }
    public Float getAverageRatingOverall() {
        List<Rating> ratingList = ratingRepository.findAll();
        float sum = 0;
        for (Rating rating : ratingList) {
            sum += rating.getGrade();
        }
        float result = sum / ratingList.size();
        BigDecimal resultRounded = new BigDecimal(result).setScale(2, RoundingMode.HALF_UP);
        result = resultRounded.floatValue();
        return result;
    }
    public void deleteOneRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
