package com.laith.hrsystem.laith.repository;

import com.laith.hrsystem.laith.model.Department;
import com.laith.hrsystem.laith.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCreationDateBetween(LocalDate from, LocalDate to);
    List<Employee> findByDepartment(Department department);

    @Query("SELECT e FROM Employee e where e.isDeleted=:isDeleted")
     List<Employee> getAllDeletedEmployee(@Param("isDeleted")boolean isDeleted);
}
