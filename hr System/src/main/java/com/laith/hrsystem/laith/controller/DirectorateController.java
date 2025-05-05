package com.laith.hrsystem.laith.controller;

import com.laith.hrsystem.laith.dto.DirectorateDto;
import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.model.Directorate;
import com.laith.hrsystem.laith.service.DirectorateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directorate")
@Log4j2
public class DirectorateController {

    @Autowired
    private DirectorateService directorateService;


    @GetMapping("/all")
    public ResponseEntity<List<Directorate>> getDirectorates() {
        List<Directorate> directorates = directorateService.getAllDepartments();
        log.info("Inside Methode getAll directorates to display All directorates");
        return new ResponseEntity<>(directorates, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Directorate> addEmployee(@RequestBody DirectorateDto directoratedto) {
        Directorate newDepartment = directorateService.addDepartment(directoratedto);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(directorateService.findDirectorateById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDirectorate(@PathVariable("id") Long id) {
        directorateService.deleteDirectorate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDirectorate(@RequestBody DirectorateDto directorateDto) {
        directorateService.updateDirectorate(directorateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
