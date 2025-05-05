package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laith.hrsystem.laith.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "department")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department extends BaseEntity<Long> {

    private String name;
    private String location;
    @OneToMany(mappedBy = "department", cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST })
    @JsonManagedReference
    private List<Employee> employees;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "directorate_department",
            joinColumns =
                    { @JoinColumn(name = "department_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "directorate_id", referencedColumnName = "id") })
    @JsonBackReference
    private Directorate directorate;
}
