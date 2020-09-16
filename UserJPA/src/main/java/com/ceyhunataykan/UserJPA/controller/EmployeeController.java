package com.ceyhunataykan.UserJPA.controller;

import java.util.List;

import com.ceyhunataykan.UserJPA.dto.AddressDTO;
import com.ceyhunataykan.UserJPA.dto.AddressDTOService;
import com.ceyhunataykan.UserJPA.dto.EmployeeDTO;
import com.ceyhunataykan.UserJPA.dto.EmployeeDTOService;
import com.ceyhunataykan.UserJPA.entity.Employee;
import com.ceyhunataykan.UserJPA.exception.GenericNotFoundException;
import com.ceyhunataykan.UserJPA.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeDTOService employeeDTOService;
    @Autowired
    private AddressDTOService addressDTOService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/employee/find-by-id/{id}")
    public ResponseEntity<Employee> getFindById(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getFindById(id), HttpStatus.OK);
    }

    @GetMapping("/dto/address_list")
    public ResponseEntity<List<AddressDTO>> getAddressAll() {
        return new ResponseEntity<>(addressDTOService.getAddressDTO(), HttpStatus.OK);
    }

    @PostMapping("/employee/add")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employee/update/{id}")
    public ResponseEntity<Object> update(@RequestBody Employee employee, @PathVariable Integer id) {
        employeeService.update(employee, id);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @PutMapping("/dto/update/{id}")
    public ResponseEntity<Employee> updateDTO(@RequestBody EmployeeDTO empDTO, @PathVariable Integer id){
        return new ResponseEntity<>(employeeDTOService.update_tc(empDTO,id), HttpStatus.OK);
    }

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) throws GenericNotFoundException {
        employeeService.delete(id);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

}
