package com.ceyhunataykan.UserJPA.dto;

import com.ceyhunataykan.UserJPA.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    @Mapping( source = "employeeTC", target = "employeeTC" )
    Employee toTarget(EmployeeDTO source);
}
