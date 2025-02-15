package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.VehicleWashType;

@Repository
@Transactional
public interface VehicleWashTypeRepository extends JpaRepository<VehicleWashType, Long> {
}
