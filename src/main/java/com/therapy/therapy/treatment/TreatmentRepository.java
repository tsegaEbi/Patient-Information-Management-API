package com.therapy.therapy.treatment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment,Long> {

    Treatment findByName(String name);
}
