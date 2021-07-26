package com.therapy.therapy.examination;

import com.therapy.therapy.laboratory.Laboratory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExaminationUpdateDTO extends ExaminationDTO{

    private List<Laboratory> laboratories;

    public static Examination GetUpdateChart(ExaminationUpdateDTO dto, Examination examination){
        if(examination==null)
            return null;

        if(dto.getChiefComplaint()!=null){

            examination.setChiefComplaint(dto.getChiefComplaint());
        }

        examination.setAssessmentAndPlan(dto.getAssessmentAndPlan());
        examination.setHistoryOfPresentIllness(dto.getHistoryOfPresentIllness());
        examination.setProgressNote(dto.getProgressNote());
        examination.setPhysicalExamination(dto.getPhysicalExamination());
        examination.setOrdersAndPrescriptions(dto.getOrdersAndPrescriptions());

        return examination;
    }

    public static Examination GetUpdateDiagnosis(ExaminationUpdateDTO dto, Examination examination){
        if(examination==null)
            return null;


        examination.setDiseaseStatement(dto.getDiseaseStatement());
        examination.setDisease(dto.getDisease());
        examination.setDiagnosisProcess(dto.getDiagnosisProcess());
        examination.setDiagnosticCategory(dto.getDiagnosticCategory());


        return examination;
    }


}
