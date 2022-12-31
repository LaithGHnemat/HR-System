package com.laith.hrsystem.laith.controller;


import com.laith.hrsystem.laith.dto.EmployeeDto;
import com.laith.hrsystem.laith.dto.LeaveDto;
import com.laith.hrsystem.laith.model.Employee;
import com.laith.hrsystem.laith.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/employee")
@Log4j2
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.findAll();
        log.info("Inside Methode getEmployees to display all employees");
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = new Employee();
        Employee newEmployee = employeeService.addEmployee(employeeDto);
        EmployeeDto employeeDto1 = EmployeeDto.toEmployeeDto(newEmployee);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/coustom/{from}/{to}")
    public ResponseEntity<?>
    findEmployeesByCreationDate(@PathVariable("from") String from, @PathVariable("to") String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateFrom = LocalDate.parse(from, formatter);
        LocalDate localDateTo = LocalDate.parse(to, formatter);
        return ResponseEntity.ok(employeeService.findEmployeeByCreationDate(localDateFrom, localDateTo));
    }

    @GetMapping("/findByDepartment/{deptId}")
    public ResponseEntity<?> getEmployeeByDeptId(@PathVariable("deptId")Long deptId ) {
        return ResponseEntity.ok(employeeService.findByDepartment(deptId));
    }

    @PostMapping("/requestLeave/{employeeId}")
    public ResponseEntity<?> requestLeave(@PathVariable("employeeId")Long employeeId,@RequestBody LeaveDto leaveDto) {
        employeeService.addLeave(employeeId,leaveDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
     @GetMapping("/getLeaves/{from}/{to}/{employeeId}")
    public ResponseEntity<?> getMyLeave(@PathVariable("from") String from,
                                        @PathVariable("to") String to,
                                        @PathVariable("employeeId") Long employeeId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateFrom = LocalDate.parse(from, formatter);
        LocalDate localDateTo = LocalDate.parse(to, formatter);
        return ResponseEntity.ok(employeeService.emttpl(localDateFrom,localDateTo,employeeId));
    }

}
