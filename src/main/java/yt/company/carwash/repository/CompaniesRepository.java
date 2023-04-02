package yt.company.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.Companies;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Integer> {
}
