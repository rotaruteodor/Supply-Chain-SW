package bGroup.SupplyChainSWbackend.repositories;

import bGroup.SupplyChainSWbackend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
