package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgress;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgressDTO;
import com.therapy.therapy.treatment.Treatment;
import com.therapy.therapy.treatment.TreatmentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class PatientTreatmentDTO {
    private Long id;
    private ExaminationDTO examination;
    private TreatmentDTO treatment;


    private TREATMENT_TYPE type;  // addmission or opd

    private Boolean started;

    private Date startedDate;

    private PATIENT_TREATMENT_RESULT result;

    private Boolean active;

    private Date completedDate;

    private String goals;
    private String note;

    private List<TreatmentProgressDTO>progresses;

    public static PatientTreatmentDTO toDetailReport(PatientTreatment  tr, List<TreatmentProgressDTO> progresses){
        PatientTreatmentDTO dto = new PatientTreatmentDTO();
        dto.setId(tr.getId());

        dto.setType(tr.getType());
        dto.setTreatment(TreatmentDTO.toDTO(tr.getTreatment()));

        dto.setResult(tr.getResult());
        dto.setStarted(tr.getStarted());
        dto.setStartedDate(tr.getStartedDate());
        dto.setActive(tr.getActive());
        dto.setCompletedDate(tr.getCompletedDate());
        dto.setGoals(tr.getGoals());
        dto.setNote(tr.getNote());

        if(progresses!=null)
            dto.setProgresses(progresses);

        return dto;
    }
    public static PatientTreatmentDTO toDTO(PatientTreatment  tr){
         PatientTreatmentDTO dto = new PatientTreatmentDTO();
         dto.setId(tr.getId());

         dto.setType(tr.getType());

         dto.setExamination(ExaminationDTO.toDTO(tr.getExamination(),null));
         dto.setTreatment(TreatmentDTO.toDTO(tr.getTreatment()));

         dto.setResult(tr.getResult());
         dto.setStarted(tr.getStarted());
         dto.setStartedDate(tr.getStartedDate());
         dto.setActive(tr.getActive());
         dto.setCompletedDate(tr.getCompletedDate());
         dto.setGoals(tr.getGoals());
         dto.setNote(tr.getNote());

         return dto;
    }
    public static PatientTreatment  toDTO(PatientTreatmentDTO  dto, PatientTreatment pt, Treatment treatment){

        if(pt==null )
            return null;
        if(dto==null) return pt;


        if(treatment.getId()!=dto.getTreatment().getId())
            pt.setTreatment(treatment);

        return pt;
    }
}
