package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User getUserByUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.id = :id")
	User getUserById(@Param("id") Long id);

	@Override
	<S extends User> S saveAndFlush(S entity);
}
