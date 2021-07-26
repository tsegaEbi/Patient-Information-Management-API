package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.treatment.TREATMENT_CATEGORY;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PatientTreatmentSearchQuery {
    private int pageNUmber;
    private Long patientId;
    private Long examinerId;
    private Long examinationId;
    private TREATMENT_TYPE type;

    private TREATMENT_CATEGORY category;
    private Long treatmentId;
    private Boolean active;

    private PATIENT_TREATMENT_RESULT result;

}
