package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.laith.hrsystem.laith.base.BaseEntity;
import com.laith.hrsystem.laith.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "leave")
@Setter
@Getter
@NoArgsConstructor
public class Leave extends BaseEntity<Long> {
    @Column(name = "creationDate")
    private LocalDate creationDate;
    private LocalTime leaveFrom;
    private LocalTime leaveTo;
    private String description;
    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus;
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    public Leave(LocalDate creationDate,
                 LocalTime leaveFrom, LocalTime leaveTo, String description,
                 LeaveStatus leaveStatus,
                 Employee employee) {
        this.creationDate = creationDate;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.description = description;
        this.leaveStatus = leaveStatus;
        this.employee = employee;
    }

    public Leave(LocalDate creationDate,
                 LocalTime leaveFrom,
                 LocalTime leaveTo,
                 String description,
                 LeaveStatus leaveStatus) {
        this.creationDate = creationDate;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.description = description;
        this.leaveStatus = leaveStatus;
    }
}
