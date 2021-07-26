package com.therapy.therapy.examination.labOrder;

import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.laboratory.Laboratory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabOrderRepository extends JpaRepository<LabOrder,Long> {

    List<LabOrder> findAllByExamination(Examination examination);

    Page<LabOrder> findByTechnician(Long id, Pageable pageable);

    @Query("FROM LabOrder l WHERE l.examination.id=:examinationId")
    Page<LabOrder> findByExamination(Long examinationId, Pageable pageable);


    @Query("FROM LabOrder l WHERE l.examination.examiner.id=:examinerId")
    Page<LabOrder> findByExaminer(Long examinerId, Pageable pageable);

    @Query("FROM LabOrder l WHERE l.examination.patientVisit.patient.id=:patientId")
    Page<LabOrder> findByPatientId(Long patientId, Pageable pageable);

    Page<LabOrder> findByLaboratory(Laboratory lab, Pageable pageable);

    @Query("FROM LabOrder l WHERE " +
            "(:examinerId is null  or l.examination.examiner.id = :examinerId)" +
            " and (:status is null  or l.orderStatus = :status)" +
            " and (:active is null  or l.active = :active)" +
            " and (:laboratoryId is null  or l.laboratory.id = :laboratoryId)" +
            " and (:technicianId is null or l.technician=:technicianId)"  )
    Page<LabOrder> searchByQuery(Long examinerId, Long technicianId, ORDER_STATUS status, Boolean active,Long laboratoryId, Pageable pageable);

    @Query("FROM LabOrder l WHERE " +
            "(:examinerId is null  or l.examination.examiner.id = :examinerId)" +
            " and (:status is null  or l.orderStatus = :status)"  )
    Page<LabOrder> getByExaminerAndStatus(Long  examinerId, ORDER_STATUS status, Pageable pageable);


}
