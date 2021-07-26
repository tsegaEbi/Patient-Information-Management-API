package com.therapy.therapy.patientTreatment.progress;

import com.therapy.therapy.patientTreatment.PatientTreatment;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentProgressCreateDTO {
    private Long patientTreatmentId;

    private String note;

    private TREATMENT_PROGRESS_POINT point;

    public static TreatmentProgress toProgress(TreatmentProgressCreateDTO dto, PatientTreatment pt, Staff reporter){

        TreatmentProgress progress=  new TreatmentProgress();

         if(pt==null || reporter==null || dto.getNote()==null) return null;

         progress.setNote(dto.getNote());
         progress.setReporter(reporter);
         progress.setPoint(dto.getPoint());
         progress.setPatientTreatment(pt);

         return progress;

    }
}
