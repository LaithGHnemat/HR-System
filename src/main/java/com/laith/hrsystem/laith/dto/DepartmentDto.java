package com.laith.hrsystem.laith.dto;

import com.laith.hrsystem.laith.model.Department;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {
    public Long id;
    public String name;
    public String location;

    public static Department fromDepartmentDto(DepartmentDto departmentDto){
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }
}
