package com.therapy.therapy.examination.labOrder.Image;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface LabOrderResultImageService extends CRUDService<LabOrderResultImage> {

    List<LabOrderResultImage> getByLabOrder(Long labOrderId);

    List<LabOrderResultImage> getByExamination(Long examinationId);


}
