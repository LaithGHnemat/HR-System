package com.laith.hrsystem.laith.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;


@Setter
@Getter
@MappedSuperclass// MappedSuperclass tells hibernate you don't have to create this entity inside the db
@EntityListeners({AuditingEntityListener.class})
 public abstract class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public ID id;

    public String statusCode;

    public boolean isDeleted;

    @CreatedBy
    public String createdBy;

    @CreatedDate
    public LocalDate creationDate;

    @LastModifiedBy
    public String lastModifiedBy;

    @LastModifiedDate
    public LocalDate lastModifiedDate;


}
