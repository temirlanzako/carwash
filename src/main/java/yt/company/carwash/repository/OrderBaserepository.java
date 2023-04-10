package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.OrderBase;


@Repository
@Transactional
public interface OrderBaserepository extends JpaRepository<OrderBase, Long> {
}
