package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.treatment.Treatment;
import com.therapy.therapy.treatment.TreatmentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter

public class PatientTreatmentCreateDTO {

    private Long examinationId;
    private TREATMENT_TYPE type;

    private String note;
    private String goals;
    private List<TreatmentDTO> treatments;



    public static List<PatientTreatment> toPT(PatientTreatmentCreateDTO dto, Examination examination, List<Treatment> treatments){
        List<PatientTreatment> pts = new ArrayList<>();

        if(dto==null || examination ==null || dto.treatments==null)
            return null;

        for(Treatment tr: treatments){
            PatientTreatment pt = new PatientTreatment();
            pt.setActive(true);
            pt.setExamination(examination);
            pt.setGoals(dto.goals);
            pt.setNote(dto.getNote());
            pt.setType(dto.getType());
            pt.setTreatment(tr);
            pt.setResult(PATIENT_TREATMENT_RESULT.PENDING);
            pts.add(pt);

        }

        return pts;
    }
}
