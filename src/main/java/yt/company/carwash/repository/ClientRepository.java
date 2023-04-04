package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.Client;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByPhone(String phone);
}
