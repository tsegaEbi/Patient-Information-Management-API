package com.therapy.therapy.examination;

import com.therapy.therapy.common.DISEASE;
import com.therapy.therapy.diagnosis.DIAGNOSIS_PROCESS;
import com.therapy.therapy.diagnosis.DIAGNOSTIC_CATEGORY;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.laboratory.Laboratory;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter

public class ExaminationCreateDTO {

    private Long patientVisitId;

    private Date examinationDate = new Date();

    private DISEASE disease;
    private String diseaseStatement;
    private String chiefComplaint;
    private String historyOfPresentIllness;
    private String physicalExamination;
    private String assessmentAndPlan;
    private String ordersAndPrescriptions;
    private String progressNote;
    private DIAGNOSIS_PROCESS diagnosisProcess;
    private DIAGNOSTIC_CATEGORY diagnosticCategory;

    private List<LabOrder> labOrders;

    private List<Laboratory>laboratoryCreates ;

    public static Examination toExamination(ExaminationCreateDTO dto, PatientVisit visit, Staff staff){

            Examination exam = new Examination();
            exam.setDisease(dto.getDisease());
            exam.setDiseaseStatement(dto.getDiseaseStatement());
            exam.setExaminer(staff);
            exam.setPatientVisit(visit);
            exam.setDateCreated(new Date());

            exam.setProgressNote(dto.getProgressNote());
            exam.setDiagnosisProcess(dto.getDiagnosisProcess());
            exam.setDiagnosticCategory(dto.getDiagnosticCategory());
            exam.setOrdersAndPrescriptions(dto.getOrdersAndPrescriptions());
            exam.setAssessmentAndPlan(dto.getAssessmentAndPlan());
            exam.setPhysicalExamination(dto.getPhysicalExamination());
            exam.setHistoryOfPresentIllness(dto.getHistoryOfPresentIllness());
            exam.setChiefComplaint(dto.getChiefComplaint());
            exam.setDiseaseStatement(dto.getDiseaseStatement());
            exam.setActive(true);


            return exam;
    }
}
