package com.laith.hrsystem.laith.controller;

import com.laith.hrsystem.laith.dto.DepartmentDto;
import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.service.DepartmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@Log4j2
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/all")
    public ResponseEntity<List<Department>> getDepartment() {
        List<Department> departments = departmentService.getAllDepartments();
        log.info("Inside Methode getAllDepartments to display All departments");
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Department> addEmployee(@RequestBody DepartmentDto departmentDto) {
        Department newDepartment = departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.findDepartmentById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateEmployee(@RequestBody DepartmentDto departmentDto) {
        departmentService.updateDepartment(departmentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/gatDepartmentByDirectorate/{directorateId}")
    public ResponseEntity<?> gatDepartmentByDirectorateId(@PathVariable("directorateId")long directorateId){
        return ResponseEntity.ok(departmentService.findByAndDirectorate_DirectorateId(directorateId));
    }

}
