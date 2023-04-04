package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.Company;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByAddress(String address);
    Company findByPhone(String phone);
}
