package com.laith.hrsystem.laith.repository;

import com.laith.hrsystem.laith.model.Directorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorateRepository extends JpaRepository<Directorate, Long> {

}
