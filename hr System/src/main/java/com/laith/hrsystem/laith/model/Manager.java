package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laith.hrsystem.laith.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

@Entity
@Table(name = "manager")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends BaseEntity<Long> {
    private String name;
    @OneToMany(mappedBy = "manager", cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST})
    @JsonManagedReference
    private List<Employee> employees;

}
