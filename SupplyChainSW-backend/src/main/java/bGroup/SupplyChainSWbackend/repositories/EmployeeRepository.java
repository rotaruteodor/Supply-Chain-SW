package bGroup.SupplyChainSWbackend.repositories;

import bGroup.SupplyChainSWbackend.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
