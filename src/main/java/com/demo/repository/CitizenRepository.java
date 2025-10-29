package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.modal.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
	boolean existsBySsn(Long ssn);
	 Optional<Citizen> findBySsn(Long ssn);
	 


}
