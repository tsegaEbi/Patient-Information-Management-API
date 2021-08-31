package com.therapy.therapy.examination.labOrder.Image;

import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.examination.labOrder.LabOrder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabOrderResultImageService extends CRUDService<LabOrderResultImage> {

    List<LabOrderResultImage> getByLabOrder(LabOrder labOrder);

    List<LabOrderResultImage> getByExamination(Long examinationId);

    List<LabOrderResultImage> findByLabOrder(LabOrder labOrder);
    List<LabOrderResultImage> findByPatient(Long patientId);

    List<LabOrderResultImage> findByVisit(Long visitId);
}
