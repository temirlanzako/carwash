package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.Rating;

import java.util.List;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findGradeByCompany_Id(Long id);
}
