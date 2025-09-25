package com.example.backend_assignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend_assignment.model.MetarData;

@Repository
public interface MetarDataRepository extends JpaRepository<MetarData, Long>{
	Optional<MetarData> findByIcaoCode(String icaoCode);
}
