package com.laith.hrsystem.laith.service;

import com.laith.hrsystem.laith.dto.DirectorateDto;
import com.laith.hrsystem.laith.exceptions.EmployeeNotFoundException;
import com.laith.hrsystem.laith.exceptions.NotFoundDirectorateException;
import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.model.Directorate;
import com.laith.hrsystem.laith.repository.DirectorateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class DirectorateService {

    @Autowired
    private DirectorateRepository directorateRepository;

    private DepartmentService departmentService;

    public List<Directorate> getAllDepartments() {
        return this.directorateRepository.findAll();
    }

    public Directorate addDepartment(DirectorateDto directorateDto) {
        Directorate directorate = DirectorateDto.fromDirectorateDto(directorateDto);
        return this.directorateRepository.save(directorate);
    }

    public void updateDirectorate(DirectorateDto directorateDto) {
        Directorate directorate = DirectorateDto.fromDirectorateDto(directorateDto);

        Department departmentById = departmentService.findDepartmentById(directorate.getDepartment().getId());
        if(Objects.nonNull(directorate.getId() ) && Objects.nonNull(departmentById))
        this.directorateRepository.save(directorate);
    }

    public Directorate findDirectorateById(Long directorateId) {
        return directorateRepository.findById(directorateId).orElseThrow(
                () -> new NotFoundDirectorateException("Directorate not found for this id :: " + directorateId));
    }

    public void deleteDirectorate(Long id) {
        Directorate directorate = directorateRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException("" +
                        "we can't delete this  Directorate because it's " + " there is no employee with this id :" + id));
        if(Objects.nonNull(directorate))
        directorateRepository.deleteById(id);
    }
}
