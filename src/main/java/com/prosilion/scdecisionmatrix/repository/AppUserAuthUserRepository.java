package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.AppUserAuthUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserAuthUserRepository extends JpaRepository<AppUserAuthUser, Integer> {
	//	@Query("SELECT e FROM AppUserAuthUser e WHERE e.appuserId = :appuserId")
	Optional<AppUserAuthUser> findByAppUserId(Integer appUserId);

	//	@Query("SELECT e FROM AppUserAuthUser e WHERE e.appuserId = :appuserId")
	Optional<AppUserAuthUser> findByAuthUserName(String authUserName);

//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE EscalationRequest set escalationId = :escalationId WHERE requestId = :requestId")
//	Integer update(Integer escalationId, Integer requestId);
}
