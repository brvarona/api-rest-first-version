package com.cashonline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cashonline.entity.User;

/**
 * User Repository
 * 
 * @author Braian Varona
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findById(Long id);
}