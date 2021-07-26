package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.treatment.Treatment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class PatientTreatmentCreateDTO {

    private Long examinationId;
    private PATIENT_TREATMENT_FREQUENCY frequency;

    private Long treatmentId;

    private TREATMENT_DOS_UNIT unit;

    private Double dose;

    private Double duration;

    private TREATMENT_TYPE type;

    private String note;



    public static PatientTreatment toPT(PatientTreatmentCreateDTO dto, Examination examination, Treatment treatment){

        if(dto==null || examination ==null || treatment==null)
            return null;
        PatientTreatment pt = new PatientTreatment();
        pt.setDose(dto.getDose());
        pt.setDuration(dto.getDuration());
        pt.setExamination(examination);
        pt.setFrequency(dto.getFrequency());
        pt.setTreatment(treatment);
        pt.setType(dto.getType());
        pt.setNote(dto.getNote());
        pt.setResult(PATIENT_TREATMENT_RESULT.PENDING);
        pt.setStarted(false);
        pt.setStartedDate(new Date());
        pt.setUnit(dto.getUnit());
        pt.setActive(false);

        return pt;
    }
}
