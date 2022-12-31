package com.laith.hrsystem.laith.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Setter
@Getter
@MappedSuperclass// this tells hibernate you don't have to create this entity inside the db
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity <ID>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private ID id;

    private String statusCode;

    private boolean isDeleted;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDate creationDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDate lastModifiedDate;


}
