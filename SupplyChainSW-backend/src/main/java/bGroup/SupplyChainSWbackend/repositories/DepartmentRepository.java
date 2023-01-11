package bGroup.SupplyChainSWbackend.repositories;

import bGroup.SupplyChainSWbackend.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
