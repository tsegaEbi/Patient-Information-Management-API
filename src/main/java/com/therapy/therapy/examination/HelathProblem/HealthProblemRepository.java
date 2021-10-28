package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.icdCode.ICD_CATEGORY;
import com.therapy.therapy.icdCode.Icd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthProblemRepository extends JpaRepository<HealthProblem,Long> {

    List<HealthProblem> findByExaminationAndIcd(Examination examination, Icd icd);

    List<HealthProblem>findByExamination(Examination examination);

    @Query("FROM HealthProblem h where h.examination.patientVisit.patient.id=:patientId")
    List<HealthProblem>getByPatientId(Long patientId);


    @Query("FROM HealthProblem l WHERE " +
            "(:icdId is null  or l.icd.id = :icdId)" +
            " and (:subCategory is null  or l.icd.subCategory.id = :subCategory)" +
            " and (:status is null  or l.status = :status)" +
            " and (:category is null  or l.icd.subCategory.category = :category)" )
    Page<HealthProblem> searchByQuery(Long icdId,Long subCategory, ICD_CATEGORY category, HEALTH_PROBLEM_STATUS status, Pageable pageable);
}
