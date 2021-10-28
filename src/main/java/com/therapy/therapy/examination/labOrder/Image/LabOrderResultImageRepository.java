package com.therapy.therapy.examination.labOrder.Image;

import com.therapy.therapy.examination.labOrder.LabOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabOrderResultImageRepository extends JpaRepository<LabOrderResultImage,Long> {
    @Query("FROM LabOrderResultImage where labOrder.id=:orderId")
    List<LabOrderResultImage> findByLabOrder(Long orderId);

    @Query("FROM LabOrderResultImage where labOrder.examination.patientVisit.patient.id=:patientId")
    List<LabOrderResultImage> findByPatient(Long patientId);

    @Query("FROM LabOrderResultImage where labOrder.examination.patientVisit.id=:visitId")
    List<LabOrderResultImage> findByVisit(Long visitId);

    @Query("FROM LabOrderResultImage where labOrder.examination.id=:examinationId")
    List<LabOrderResultImage> findByExamination(Long examinationId);
}
