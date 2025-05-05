package com.laith.hrsystem.laith.repository;

import com.laith.hrsystem.laith.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
//I made CREATION_DATE in this way because H2 DB makes all columns like this, and here JPQL comes to solve this issue
    @Query(value = "select * from leave where CREATION_DATE  between :from and :to " +
            "AND EMPLOYEE_ID= :empId",nativeQuery = true)
    List<Leave> filter(@Param("from")LocalDate from,
                      @Param("to") LocalDate to,
                      @Param("empId")Long employeeId);
}
