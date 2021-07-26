package com.therapy.therapy.patientTreatment.progress;

import com.therapy.therapy.patientTreatment.PatientTreatmentDTO;
import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TreatmentProgressDTO {
    private Long id;
    private PatientTreatmentDTO patientTreatment;

    private String note;
    private TREATMENT_PROGRESS_POINT point;

    private StaffDTO reporter;

    private Date dateCreated;


    public static TreatmentProgressDTO toDTO(TreatmentProgress treatmentProgress){

        TreatmentProgressDTO progress = new  TreatmentProgressDTO();

        progress.setId(treatmentProgress.getId());
        progress.setNote(treatmentProgress.getNote());

        progress.setPoint(treatmentProgress.getPoint());
        progress.setPatientTreatment(PatientTreatmentDTO.toDTO(treatmentProgress.getPatientTreatment()));

        progress.setDateCreated(treatmentProgress.getDateCreated());

        progress.setReporter(StaffDTO.toDTO(treatmentProgress.getReporter()));

        return progress;
    }
}
