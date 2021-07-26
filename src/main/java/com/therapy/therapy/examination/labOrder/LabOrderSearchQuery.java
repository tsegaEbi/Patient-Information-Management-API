package com.therapy.therapy.examination.labOrder;

import com.therapy.therapy.common.ORDER_STATUS;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LabOrderSearchQuery {


    private int pageNumber;
    private Long patientId;
    private Long laboratoryId;
    private Long technicianId;
    private Long examinationId;
    private ORDER_STATUS orderStatus;
    private Boolean active;
    private Long examinerId;
}
