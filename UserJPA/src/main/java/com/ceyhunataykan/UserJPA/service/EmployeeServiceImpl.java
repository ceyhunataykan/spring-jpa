package com.ceyhunataykan.UserJPA.service;

import java.util.List;
import java.util.Optional;

import com.ceyhunataykan.UserJPA.entity.Employee;
import com.ceyhunataykan.UserJPA.exception.GenericNotFoundException;
import com.ceyhunataykan.UserJPA.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employeeBody) {
        return employeeRepository.save(employeeBody);
    }

    @Override
    public void update(Employee employee, Integer id) {
        Employee optionalEmployee = getFindById(id);
        if (!(optionalEmployee == null)) {
            employee.setEmployeeId(id);
            employeeRepository.save(employee);
        } else {
            throw new GenericNotFoundException();
        }
    }

    @Override
    public void delete(Integer id) {
        Employee optionalEmployee = getFindById(id);
        if (!(optionalEmployee == null)) {
            employeeRepository.deleteById(id);
        } else {
            throw new GenericNotFoundException();
        }
    }

    @Override
    public Employee getFindById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new GenericNotFoundException();
        }
        return optionalEmployee.get();
    }

}
