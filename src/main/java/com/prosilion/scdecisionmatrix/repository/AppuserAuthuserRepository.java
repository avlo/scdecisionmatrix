package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.AppuserAuthuser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppuserAuthuserRepository extends JpaRepository<AppuserAuthuser, Integer> {
	//	@Query("SELECT e FROM AppuserAuthuser e WHERE e.appuserId = :appuserId")
	Optional<AppuserAuthuser> findByAppuserId(Integer appuserId);

	//	@Query("SELECT e FROM AppuserAuthuser e WHERE e.appuserId = :appuserId")
	Optional<AppuserAuthuser> findByAuthuserName(String authuserName);

//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE EscalationRequest set escalationId = :escalationId WHERE requestId = :requestId")
//	Integer update(Integer escalationId, Integer requestId);
}
