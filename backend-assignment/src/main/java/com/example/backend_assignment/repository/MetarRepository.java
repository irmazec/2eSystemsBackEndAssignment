package com.example.backend_assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_assignment.model.Metar;

public interface MetarRepository extends JpaRepository<Metar, Long> {
	Optional<Metar> findBySubscriptionId(Long id);
}
