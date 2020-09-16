package com.ceyhunataykan.UserJPA.dto;

import com.ceyhunataykan.UserJPA.entity.Employee;
import com.ceyhunataykan.UserJPA.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressDTOImpl implements AddressDTOService {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public List<AddressDTO> getAddressDTO() {
        List<Employee> emp = employeeService.getAll();
        List<AddressDTO> addressDTOS = new ArrayList<>();

        for (int i = 0; i < emp.size(); i++) {
            addressDTOS.add(new AddressDTO(emp.get(i).getEmployeeId(),
                    emp.get(i).getAddress().getCity(),
                    emp.get(i).getAddress().getDoorNumber(),
                    emp.get(i).getAddress().getStreet()));
        }
        return addressDTOS;
    }
}
