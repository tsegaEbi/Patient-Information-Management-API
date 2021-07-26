package com.therapy.therapy.patient.checkup;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PatientVisitSearchQuery {

    private int pageNumber;
    private Long departmentId;
    private Long examinerId;
    private Long patientId;

    private Boolean examined;
    private Boolean assigned;

    private String key;
}
