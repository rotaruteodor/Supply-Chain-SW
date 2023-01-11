package bGroup.SupplyChainSWbackend.repositories;

import bGroup.SupplyChainSWbackend.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
