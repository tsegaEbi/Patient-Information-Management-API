package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.treatment.Treatment;
import com.therapy.therapy.treatment.TreatmentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PatientTreatmentDTO {
    private Long id;
    private ExaminationDTO examination;
    private PATIENT_TREATMENT_FREQUENCY frequency;

    private TreatmentDTO treatment;

    private TREATMENT_DOS_UNIT unit;

    private Double dose;

    private Double duration;

    private TREATMENT_TYPE type;  // addmission or opd

    private Boolean started;

    private Date startedDate;

    private PATIENT_TREATMENT_RESULT result;

    private Boolean active;

    private Date completedDate;
    //for updating
    private Long treatmentId;


    public static PatientTreatmentDTO toDTO(PatientTreatment  tr){
         PatientTreatmentDTO dto = new PatientTreatmentDTO();
         dto.setId(tr.getId());
         dto.setDose(tr.getDose());
         dto.setDuration(tr.getDuration());
         dto.setFrequency(tr.getFrequency());
         dto.setType(tr.getType());
         dto.setUnit(tr.getUnit());

         dto.setExamination(ExaminationDTO.toDTO(tr.getExamination(),null));
         dto.setTreatment(TreatmentDTO.toDTO(tr.getTreatment()));

         dto.setResult(tr.getResult());
         dto.setStarted(tr.getStarted());
         dto.setStartedDate(tr.getStartedDate());
         dto.setActive(tr.getActive());
         dto.setCompletedDate(tr.getCompletedDate());

         return dto;
    }
    public static PatientTreatment  toDTO(PatientTreatmentDTO  dto, PatientTreatment pt, Treatment treatment){

        if(pt==null )
            return null;
        if(dto==null) return pt;

        if(dto.getDose()!=null)
            pt.setDose(dto.getDose());

        if(dto.getFrequency()!=null)
            pt.setFrequency(dto.getFrequency());

        if(dto.getDuration()!=null)
            pt.setDuration(dto.getDuration());

        if(treatment.getId()!=dto.getTreatment().getId())
            pt.setTreatment(treatment);

        return pt;
    }
}
