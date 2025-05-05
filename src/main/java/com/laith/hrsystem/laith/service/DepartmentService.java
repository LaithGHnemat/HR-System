package com.laith.hrsystem.laith.service;

import com.laith.hrsystem.laith.dto.DepartmentDto;
import com.laith.hrsystem.laith.exceptions.NotFoundDepartmentException;
import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.repository.DepartmentRepository;
import com.laith.hrsystem.laith.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Log
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeService;

    @Autowired
    private DirectorateService directorateService;

    public List<Department> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    public Department addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentDto.fromDepartmentDto(departmentDto);
        department.setCreationDate(LocalDate.now());
        log.info("Inside Methode getAllDepartments to display All departments");
        return this.departmentRepository.save(department);
    }


    public Department updateDepartment(DepartmentDto departmentDto) {

        Department department = departmentRepository.findById(departmentDto.getId()).orElseThrow(
                () -> new NotFoundDepartmentException("you cant update department with  id " + departmentDto.getId()
                        + " because there is no department with such id" + departmentDto.getId()));
        department.setLocation(departmentDto.getLocation());
        department.setName(departmentDto.getName());
        log.info("the Department has been update Successfully");
        return this.departmentRepository.save(department);
    }


    public Department findDepartmentById(Long departmentId) {
        log.info("Inside findDepartmentById.....");
        return departmentRepository.findById(departmentId).orElseThrow(
                () -> new NotFoundDepartmentException("Department not found for this id :: " + departmentId));
    }

    public Map<String, Boolean> deleteDepartment(Long departmentId) throws NotFoundDepartmentException {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new NotFoundDepartmentException("Department not found for this id :: " + departmentId));
        department.getEmployees().forEach((emp) -> {
            emp.setDepartment(null);
            this.employeeService.save(emp);
        });
        departmentRepository.delete(department);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private void departmentNotExist(Long id, String message) {
        departmentNotExist(id, "you cant delete employee with id " + id + " because there is no employee with such id");
        departmentRepository.findById(id).orElseThrow(() -> new NotFoundDepartmentException(message));
    }


    public Department findByAndDirectorate_DirectorateId(long directorateId){
       return departmentRepository.findByDirectorate(directorateService.findDirectorateById(directorateId));
    }
}
