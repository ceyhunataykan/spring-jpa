package com.ceyhunataykan.UserJPA.service;

import com.ceyhunataykan.UserJPA.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAll();
    public Employee save(Employee employee);
    public void update(Employee employee, Integer id);
    public void delete(Integer id);
    public Employee getFindById(Integer id);
}
