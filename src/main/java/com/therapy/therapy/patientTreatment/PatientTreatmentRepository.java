package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.treatment.TREATMENT_CATEGORY;
import com.therapy.therapy.treatment.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientTreatmentRepository extends JpaRepository<PatientTreatment,Long> {

    List<PatientTreatment> findByExaminationAndTreatment(Examination examination, Treatment treatment);

    List<PatientTreatment> findByExamination(Examination examination);

    @Query("FROM PatientTreatment l WHERE " +
            "(:examinerId is null  or l.examination.examiner.id = :examinerId)" +
            " and (:patientId is null  or l.examination.patientVisit.patient.id = :patientId)" +
            " and (:treatmentId is null  or l.treatment.id = :treatmentId)" +
            " and (:category is null  or l.treatment.category = :category)" +
            " and (:active is null  or l.active = :active)" +
            " and (:result is null  or l.result = :result)" +
            " and (:type is null  or l.type = :type)"  )
    Page<PatientTreatment> searchByQuery(Long patientId, Long examinerId, Long treatmentId,
                                         TREATMENT_TYPE type, TREATMENT_CATEGORY category,
                                         Boolean active, PATIENT_TREATMENT_RESULT result,Pageable pageable);

    @Query("FROM PatientTreatment l WHERE " +
            "(:examinerId is null  or l.examination.examiner.id = :examinerId)" +
            " and (:result is null  or l.result = :result) "  )
    Page<PatientTreatment> searchByExaminerAndStatus(Long examinerId,PATIENT_TREATMENT_RESULT result,Pageable pageable);

}
