package com.therapy.therapy.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaboratoryRepository extends JpaRepository<Laboratory,Long> {

    List<Laboratory> findByName(String name);
}
