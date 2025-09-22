package com.example.backend_assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_assignment.model.Metar;

public interface MetarRepository extends JpaRepository<Metar, Integer> {
	Optional<Metar> findBySubscriptionId(Long id);
}
