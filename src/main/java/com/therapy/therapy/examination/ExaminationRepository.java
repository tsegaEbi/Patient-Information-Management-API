package com.therapy.therapy.examination;

import com.therapy.therapy.patient.checkup.PatientVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination,Long> {

    @Query("FROM Examination e WHERE e.patientVisit.patient.id=:patientId")
    List<Examination> findByPatientId(Long patientId);

    Examination findByPatientVisit(PatientVisit visit);


}
