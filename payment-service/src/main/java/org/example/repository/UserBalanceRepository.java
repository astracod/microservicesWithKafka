package org.example.repository;

import org.example.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserBalanceRepository extends JpaRepository<UserBalance, UUID> {
}
