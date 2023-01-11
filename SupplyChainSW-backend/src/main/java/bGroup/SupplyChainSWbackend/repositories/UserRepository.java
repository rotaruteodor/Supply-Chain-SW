package bGroup.SupplyChainSWbackend.repositories;

import bGroup.SupplyChainSWbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
