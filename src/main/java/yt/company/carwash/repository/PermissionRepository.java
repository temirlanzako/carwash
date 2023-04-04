package yt.company.carwash.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yt.company.carwash.models.Permission;

import java.util.List;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}
