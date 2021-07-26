package com.therapy.therapy.appointment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppointmentSearchQueryDTO {

    private int pageNumber;
    private Long examinerId;
    private Long patientId;
    private Long departmentId;
    private Boolean active;
    private APPOINTMENT_PURPOSE purpose;

    private Boolean coming;
    private String key;

}
