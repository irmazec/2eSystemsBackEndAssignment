package com.example.backend_assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.backend_assignment.model.Subscription;

@Repository
public interface AirportSubscriptionsRepository extends JpaRepository<Subscription, Long>, JpaSpecificationExecutor<Subscription> {
	Optional<Subscription> findByIcaoCode(String codeName);
}
