package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit,Long> {

    List<PatientVisit> findByPatient(Patient patient);

    @Query("FROM PatientVisit p WHERE " +
            "(:patient_id IS NULL OR p.patient.id=:patient_id) " +
            " and (:department is null  or p.department =:department)" +
            " and (:examined is null  or p.examined =:examined)" +
            " and (:examiner is null or p.examiner=:examiner)")
    Page<PatientVisit> searchByQuery(Long patient_id,Long examiner, Long department,Boolean examined,   Pageable pageable);

    @Query("FROM PatientVisit p where lower(p.patient.firstName) like lower(concat('%',:key,'%')) " +
            "or lower(p.patient.fatherName) like lower(concat('%',:key,'%')) or " +
            "lower(p.patient.lastName) like lower(concat('%',:key,'%')) or " +
            "lower(p.patient.mobile) like lower(concat('%',:key,'%'))")
    Page<PatientVisit> searchKey(String key, Pageable pageable);

    Page<PatientVisit> findByDepartmentAndExamined(Long departmentId,Boolean examined, Pageable pageable);

    Page<PatientVisit> findByExaminerAndExamined(Long examinerId,Boolean examined, Pageable pageable);

    Page<PatientVisit> findByExamined(boolean examined, Pageable pageable);
}
