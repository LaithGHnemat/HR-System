
package com.laith.hrsystem.laith.dto;

import com.laith.hrsystem.laith.model.Employee;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    public Long id;
    public String name;
    public String email;
    public double salary;
    public String creationDate;
    public Long departmentId;
    public String departmentName;

    public static EmployeeDto toEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setName(employee.getName());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setCreationDate(employee.getCreationDate().toString());
        employeeDto.setDepartmentId(employee.getDepartment().getId());
        employeeDto.setDepartmentName(employee.getDepartment().getName());
        return employeeDto;
    }

    public static Employee fromEmployeeDto(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setEmail(employeeDto.getEmail());
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }

}

