package com.laith.hrsystem.laith.repository;

import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.model.Directorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDirectorate(Directorate directorate);
}
