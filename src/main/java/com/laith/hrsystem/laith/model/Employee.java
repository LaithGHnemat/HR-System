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
import java.util.Set;

@Entity
@Table(name = "employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private static final long serialVersionUID = 5L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private double salary;
    LocalDate creationDate;
    @ManyToOne()
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;
    @OneToMany(mappedBy = "employee",
            cascade = {CascadeType.DETACH,
                    CascadeType.REFRESH,
            CascadeType.MERGE,
                    CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Leave> leaves;

    @ManyToOne()
    @JoinColumn(name = "manager_id")
    @JsonBackReference
    private Manager manager;


    public Employee(String name, String email, double salary, LocalDate creationDate, Department department,Manager manager) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.creationDate = creationDate;
        this.department = department;
        this.manager=manager;
    }

    public Employee(String name, String email, double salary, LocalDate creationDate, Department department, Set<Leave> leaves) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.creationDate = creationDate;
        this.department = department;
        this.leaves = leaves;
    }

    public void removeLeaves() {
        for (Leave leave : leaves) {
            leave.setEmployee(null);
        }
        this.leaves.clear();
    }
}
