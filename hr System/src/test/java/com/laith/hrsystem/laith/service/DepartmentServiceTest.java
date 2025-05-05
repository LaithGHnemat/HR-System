package com.laith.hrsystem.laith.service;

import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DepartmentServiceTest {
    @InjectMocks
    DepartmentService departmentService;

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    public void findDepartmentByIdTest() {
        Department department1 = new Department();
        department1.setId(1L);
        department1.setLocation("Amman");
        department1.setName("IT");
        Mockito.when(departmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(department1));
          Department  department= departmentService.findDepartmentById(44L);
        assertEquals("IT", department.getName());
        // note: the department with id 44L will return even our dept id is 1L case : we use Mockito.anyLong()
    }

}
