package com.ceyhunataykan.UserJPA.repository;

import com.ceyhunataykan.UserJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
