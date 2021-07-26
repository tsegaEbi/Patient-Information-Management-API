package com.therapy.therapy.patient.checkup;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PatientVisitAssignmentCreate {
    private Long patientVisitId;
    private Long assigneeId;

    private VISIT_ASSIGNMENT_CODE code;
}
