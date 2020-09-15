package com.ceyhunataykan.UserJPA.repository;

import com.ceyhunataykan.UserJPA.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
