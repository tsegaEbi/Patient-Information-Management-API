package com.therapy.therapy.examination.patientHealthProblem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthProblemRepository extends JpaRepository<HealthProblem,Long> {
}
