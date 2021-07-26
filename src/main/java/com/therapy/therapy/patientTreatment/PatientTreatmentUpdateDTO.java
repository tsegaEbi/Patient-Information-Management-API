package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.treatment.Treatment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientTreatmentUpdateDTO {

    private Long id;
    private Double dose;
    private Double duration;
    private PATIENT_TREATMENT_RESULT result;
    private PATIENT_TREATMENT_FREQUENCY frequency;
    private Long treatmentId;
    private TREATMENT_DOS_UNIT unit;


    public static PatientTreatment  toDTO(PatientTreatmentUpdateDTO  dto, PatientTreatment pt, Treatment treatment){

        if(pt==null )
            return null;
        if(dto==null) return pt;

        if(dto.getDose()!=null)
            pt.setDose(dto.getDose());

        if(dto.getFrequency()!=null)
            pt.setFrequency(dto.getFrequency());

        if(dto.getDuration()!=null)
            pt.setDuration(dto.getDuration());
        if(dto.getUnit()!=null)
            pt.setUnit(dto.getUnit());

        if(treatment.getId()!=pt.getTreatment().getId())
            pt.setTreatment(treatment);

        if(dto.getResult()!=null && pt.getResult()!=dto.getResult()){


            pt.setResult(dto.result);
        }

        return pt;
    }
}
