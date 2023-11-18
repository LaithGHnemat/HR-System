package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.laith.hrsystem.laith.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "directorate")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Directorate extends BaseEntity<Long> {
    private String name;
    private String center;
    private String phone;
    @OneToOne(mappedBy = "directorate")
    @JsonManagedReference
    private Department department;
}
