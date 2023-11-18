package com.laith.hrsystem.laith.service;

import com.laith.hrsystem.laith.dto.EmployeeDto;
import com.laith.hrsystem.laith.dto.LeaveDto;
import com.laith.hrsystem.laith.exceptions.EmployeeNotFoundException;
import com.laith.hrsystem.laith.exceptions.NotFoundDepartmentException;
import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.model.Employee;
import com.laith.hrsystem.laith.model.Leave;
import com.laith.hrsystem.laith.repository.DepartmentRepository;
import com.laith.hrsystem.laith.repository.EmployeeRepository;
import com.laith.hrsystem.laith.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    LeaveRepository leaveRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeDto.fromEmployeeDto(employeeDto);
        employee.setCreationDate(LocalDate.now());
        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new NotFoundDepartmentException("Employee by id "
                        + employeeDto.getDepartmentId() + " was not found"));
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeDto.fromEmployeeDto(employeeDto);
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                        new EmployeeNotFoundException("Employee by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("" +
                        "we can't delete this project couz it's " +
                        " there is no employee with this id :" + id));
        employee.removeLeaves();
        employeeRepository.deleteById(id);
    }

    public List<Employee> findEmployeeByCreationDate(LocalDate from, LocalDate to) {
        return employeeRepository.findByCreationDateBetween(from, to);
    }


    public List<Employee> findByDepartment(Long departmentId) {
        if(Objects.isNull(departmentId))
            return Collections.emptyList();
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isPresent())
        return employeeRepository.findByDepartment(department.get());
        return Collections.emptyList();
    }

    @Transactional
    public void addLeave(Long employeeId, LeaveDto leavedto) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
                -> new EmployeeNotFoundException("there is no such emp"));
        //todo make some cheaks that the list is not
        Set<Leave> leaves = new HashSet<>(employee.getLeaves());
        Leave leave = LeaveDto.fromLeaveDto(leavedto);
        leave.setEmployee(employee);
        leaves.add(leave);
        employee.setLeaves(leaves);
        employeeRepository.save(employee);

    }

    public List<Leave> emttpl(LocalDate from, LocalDate to, Long employeeId) {
        return leaveRepository.filter(from, to, employeeId);
    }

    public List<Leave> gatUsingJPQL(LocalDate from, LocalDate to, Long employeeId) {
        return leaveRepository.getListByCreationDateBetweenAndEmployeeId(from, to, employeeId);
    }

    /// soft delete and get the soft deleted employees.
    public void softDelete(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("" +
                        "we can't delete this project couz it's " +
                        " there is no employee with this id :" + id));
        employee.setDeleted(true);
        employeeRepository.save(employee);

      //  employeeRepository.deleteById(id);
    }


    public List<Employee> gatAllDeletedEmployee() {
        return employeeRepository.getAllDeletedEmployee(true);
    }
}
