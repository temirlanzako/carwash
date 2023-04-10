package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.WebClient;

@Repository
@Transactional
public interface WebClientRepository extends JpaRepository<WebClient, Long> {
    WebClient findByPhone(String phone);
}
