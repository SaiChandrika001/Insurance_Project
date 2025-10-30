package com.example.repo;






import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.module.SSN;
@Repository
public interface SsnRepo extends JpaRepository<SSN, Long>{

	
//	boolean existsBySsn(Long ssn);
//
//	Object findBySsnNumber(Long ssnnumber);
	
//	@Query(value = "SELECT * FROM SSN_Data WHERE SSN = :ssn", nativeQuery = true)
//	List<SSN> findBySsn(@Param("ssn") Long ssn);

//	Optional<SSN> findBySsnAndFullnameAndStatename(Long ssn, String fullname, String statename);

	 Optional<SSN> findBySsn(Long ssn);
	 
	 
	 

	
}
