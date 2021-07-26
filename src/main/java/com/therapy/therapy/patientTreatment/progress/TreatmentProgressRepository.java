package com.therapy.therapy.patientTreatment.progress;

import com.therapy.therapy.patientTreatment.PatientTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentProgressRepository extends JpaRepository<TreatmentProgress,Long> {

    List<TreatmentProgress> findByPatientTreatment(PatientTreatment patientTreatment);

    @Query("FRom TreatmentProgress t WHERE t.patientTreatment.examination.id=:examinationId")
    List<TreatmentProgress>findByExamination(Long examinationId);
}
