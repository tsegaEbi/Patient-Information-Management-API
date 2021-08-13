package com.therapy.therapy.examination;

import com.therapy.therapy.common.DISEASE;
import com.therapy.therapy.diagnosis.DIAGNOSIS_PROCESS;
import com.therapy.therapy.diagnosis.DIAGNOSTIC_CATEGORY;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.laboratory.LaboratoryDTO;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class ExaminationDTO {
    private Long id;

    private StaffDTO examiner;
    private PatientVisitDTO visit;
    private Long patientVisitId;

    private DISEASE disease;

    private String diseaseStatement;
    private String findings;

    private List<LabOrderDTO> labOrders;

    private Date dateCreated;


    private String chiefComplaint;
    private String historyOfPresentIllness;
    private String physicalExamination;
    private String assessmentAndPlan;
    private String ordersAndPrescriptions;
    private String progressNote;
    private DIAGNOSIS_PROCESS diagnosisProcess;
    private DIAGNOSTIC_CATEGORY diagnosticCategory;

    private Boolean active;

    private String functionalLimitationActive;
    private String functionalLimitationPassive;
    private String functionalLimitationMotor;
    private String functionalLimitationSensation;
    private String functionalLimitationReflex;
    private String functionalLimitationOverPressure;



    public static ExaminationDTO toDTO(Examination examination, List<LaboratoryDTO>labOrders ){
        if(examination!=null) {
            ExaminationDTO dto = new ExaminationDTO();

            dto.setFunctionalLimitationActive(examination.getFunctionalLimitationActive());
            dto.setFunctionalLimitationPassive(examination.getFunctionalLimitationPassive());
            dto.setFunctionalLimitationMotor(examination.getFunctionalLimitationMotor());
            dto.setFunctionalLimitationOverPressure(examination.getFunctionalLimitationOverPressure());
            dto.setFunctionalLimitationReflex(examination.getFunctionalLimitationReflex());
            dto.setFunctionalLimitationSensation(examination.getFunctionalLimitationSensation());


            dto.setId(examination.getId());
            dto.setDisease(examination.getDisease());
            dto.setDiseaseStatement(examination.getDiseaseStatement());

            dto.setExaminer(StaffDTO.toDTO(examination.getExaminer()));

            dto.setVisit(PatientVisitDTO.toPatientVisitDTO(
                    examination.getPatientVisit(),null,null,null
            ));
            dto.setDateCreated(examination.getDateCreated());

            dto.setProgressNote(examination.getProgressNote());
            dto.setDiagnosisProcess(examination.getDiagnosisProcess());
            dto.setDiagnosticCategory(examination.getDiagnosticCategory());
            dto.setOrdersAndPrescriptions(examination.getOrdersAndPrescriptions());
            dto.setAssessmentAndPlan(examination.getAssessmentAndPlan());
            dto.setPhysicalExamination(examination.getPhysicalExamination());
            dto.setHistoryOfPresentIllness(examination.getHistoryOfPresentIllness());
            dto.setChiefComplaint(examination.getChiefComplaint());
            dto.setDiseaseStatement(examination.getDiseaseStatement());

            dto.setActive(examination.getActive());

            return dto;
        }
        return null;
    }
    public static ExaminationDTO toDTOLab(Examination examination, List<LabOrder>labOrders ){
        if(examination!=null) {
            ExaminationDTO dto = new ExaminationDTO();
            dto.setFunctionalLimitationActive(examination.getFunctionalLimitationActive());
            dto.setFunctionalLimitationPassive(examination.getFunctionalLimitationPassive());
            dto.setFunctionalLimitationMotor(examination.getFunctionalLimitationMotor());
            dto.setFunctionalLimitationOverPressure(examination.getFunctionalLimitationOverPressure());
            dto.setFunctionalLimitationReflex(examination.getFunctionalLimitationReflex());
            dto.setFunctionalLimitationSensation(examination.getFunctionalLimitationSensation());

            dto.setId(examination.getId());
            dto.setDisease(examination.getDisease());
            dto.setDiseaseStatement(examination.getDiseaseStatement());

            dto.setExaminer(StaffDTO.toDTO(examination.getExaminer()));

            dto.setVisit(PatientVisitDTO.toPatientVisitDTO(
                    examination.getPatientVisit(),null,null,null
            ));
            dto.setDateCreated(examination.getDateCreated());

            dto.setProgressNote(examination.getProgressNote());
            dto.setDiagnosisProcess(examination.getDiagnosisProcess());
            dto.setDiagnosticCategory(examination.getDiagnosticCategory());
            dto.setOrdersAndPrescriptions(examination.getOrdersAndPrescriptions());
            dto.setAssessmentAndPlan(examination.getAssessmentAndPlan());
            dto.setPhysicalExamination(examination.getPhysicalExamination());
            dto.setHistoryOfPresentIllness(examination.getHistoryOfPresentIllness());
            dto.setChiefComplaint(examination.getChiefComplaint());
            dto.setDiseaseStatement(examination.getDiseaseStatement());

            dto.setActive(examination.getActive());

            if(labOrders!=null && labOrders.size()>0){
                dto.setLabOrders(labOrders.stream().map(l->LabOrderDTO.toDTO(l,null)).collect(Collectors.toList()));
            }

            return dto;
        }
        return null;
    }

    public static ExaminationDTO toDetail(Examination examination, List<LabOrderDTO>labOrders ){
        if(examination!=null) {
            ExaminationDTO dto = new ExaminationDTO();
            dto.setId(examination.getId());
            dto.setDisease(examination.getDisease());
            dto.setDiseaseStatement(examination.getDiseaseStatement());

            dto.setExaminer(StaffDTO.toDTO(examination.getExaminer()));

            dto.setVisit(PatientVisitDTO.toDTOBasic(
                    examination.getPatientVisit(),null
            ));
            dto.setDateCreated(examination.getDateCreated());

            dto.setProgressNote(examination.getProgressNote());
            dto.setDiagnosisProcess(examination.getDiagnosisProcess());
            dto.setDiagnosticCategory(examination.getDiagnosticCategory());
            dto.setOrdersAndPrescriptions(examination.getOrdersAndPrescriptions());
            dto.setAssessmentAndPlan(examination.getAssessmentAndPlan());
            dto.setPhysicalExamination(examination.getPhysicalExamination());
            dto.setHistoryOfPresentIllness(examination.getHistoryOfPresentIllness());
            dto.setChiefComplaint(examination.getChiefComplaint());
            dto.setDiseaseStatement(examination.getDiseaseStatement());

            dto.setFunctionalLimitationActive(examination.getFunctionalLimitationActive());
            dto.setFunctionalLimitationPassive(examination.getFunctionalLimitationPassive());
            dto.setFunctionalLimitationMotor(examination.getFunctionalLimitationMotor());
            dto.setFunctionalLimitationOverPressure(examination.getFunctionalLimitationOverPressure());
            dto.setFunctionalLimitationReflex(examination.getFunctionalLimitationReflex());
            dto.setFunctionalLimitationSensation(examination.getFunctionalLimitationSensation());

            dto.setActive(examination.getActive());

            if(labOrders!=null && labOrders.size()>0){
                dto.setLabOrders(labOrders);
            }

            return dto;
        }
        return null;
    }
}
