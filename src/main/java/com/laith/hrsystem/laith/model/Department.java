package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "department")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {
    private static final long serialVersionUID = 6L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String location;
    LocalDate creationDate;
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
