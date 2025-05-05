package com.laith.hrsystem.laith.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Directorate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String center;
    private String phone;
    @OneToOne(mappedBy = "directorate")
    @JsonManagedReference
    private Department department;

}
