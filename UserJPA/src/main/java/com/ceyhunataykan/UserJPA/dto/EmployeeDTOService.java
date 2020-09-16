package com.ceyhunataykan.UserJPA.dto;

import com.ceyhunataykan.UserJPA.entity.Employee;

public interface EmployeeDTOService {
    Employee update_tc(EmployeeDTO employeeDTO, Integer id);
}
