package com.ceyhunataykan.UserJPA.controller;

import java.util.List;

import com.ceyhunataykan.UserJPA.dto.EmployeeDTO;
import com.ceyhunataykan.UserJPA.dto.EmployeeDTOService;
import com.ceyhunataykan.UserJPA.dto.EmployeeMapper;
import com.ceyhunataykan.UserJPA.entity.Employee;
import com.ceyhunataykan.UserJPA.exception.GenericNotFoundException;
import com.ceyhunataykan.UserJPA.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private EmployeeMapper employeeMapper;

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @PutMapping("/dto/update/{id}")
    public ResponseEntity<EmployeeDTO> updateDTO(@RequestBody EmployeeDTO empDTO, @PathVariable Integer id){
        Employee t = EmployeeMapper.MAPPER.toTarget(empDTO);
        t.setEmployeeId(id);
        employeeService.save(t);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(empDTO);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/employee/find-by-id/{id}")
    public ResponseEntity<Employee> getFindById(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.getFindById(id), HttpStatus.OK);
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

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer id) throws GenericNotFoundException {
        employeeService.delete(id);
        return new ResponseEntity<>("true", HttpStatus.OK);
    }

}
