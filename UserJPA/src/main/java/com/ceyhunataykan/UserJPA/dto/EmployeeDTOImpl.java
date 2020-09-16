package com.ceyhunataykan.UserJPA.dto;

import com.ceyhunataykan.UserJPA.entity.Employee;
import com.ceyhunataykan.UserJPA.exception.GenericNotFoundException;
import com.ceyhunataykan.UserJPA.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDTOImpl implements EmployeeDTOService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Employee update_tc(EmployeeDTO employeeDTO, Integer id) {
        ModelMapper modelMapper = new ModelMapper();

        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setEmployeeTC(employeeDTO.getEmployeeTC());
        Employee oldEmp = employeeService.getFindById(id);
        if (oldEmp != null) {
            employee.setEmployeeId(id);
            employee.setEmployeeName(oldEmp.getEmployeeName());
            employee.setAddress(oldEmp.getAddress());
            employeeService.save(employee);
        } else {
            throw new GenericNotFoundException();
        }
        return employee;
    }
}
