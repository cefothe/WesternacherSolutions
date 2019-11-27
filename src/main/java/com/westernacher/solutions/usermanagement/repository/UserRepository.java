package com.westernacher.solutions.usermanagement.repository;

import com.westernacher.solutions.usermanagement.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository is responsible to contain all queries that is related to {@link User}
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
