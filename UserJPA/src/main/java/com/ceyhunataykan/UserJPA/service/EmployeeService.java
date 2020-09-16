package com.ceyhunataykan.UserJPA.service;

import com.ceyhunataykan.UserJPA.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee save(Employee employee);
    void update(Employee employee, Integer id);
    void delete(Integer id);
    Employee getFindById(Integer id);
}
