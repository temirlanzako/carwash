package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.Review;

import java.util.List;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
