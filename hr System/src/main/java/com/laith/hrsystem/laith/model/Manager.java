package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laith.hrsystem.laith.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "manager")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Manager implements Serializable {
    private static final long serialVersionUID = 7L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "manager", cascade = {CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private List<Employee> employees;

}
