package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.treatment.Treatment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientTreatmentUpdateDTO {

    private Long id;
    private PATIENT_TREATMENT_RESULT result;


    public static PatientTreatment  toDTO(PatientTreatmentUpdateDTO  dto, PatientTreatment pt, Treatment treatment){

        if(pt==null )
            return null;
        if(dto==null) return pt;



        if(treatment.getId()!=pt.getTreatment().getId())
            pt.setTreatment(treatment);

        if(dto.getResult()!=null && pt.getResult()!=dto.getResult()){


            pt.setResult(dto.result);
        }

        return pt;
    }
}
