package com.prosilion.scdecisionmatrix.repository;

import com.prosilion.scdecisionmatrix.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(@NonNull String username);
}
