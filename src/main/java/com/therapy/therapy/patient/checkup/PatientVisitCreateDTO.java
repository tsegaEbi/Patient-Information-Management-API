package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.common.PATIENT_VISIT_METHOD;
import com.therapy.therapy.common.PREGNANCY_STATUS;
import com.therapy.therapy.common.VISIT_PROBLEM_CAUSE;
import com.therapy.therapy.common.YES_OR_NO;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PatientVisitCreateDTO {
    private Long patientId;

    private String healthProblem;
    private Date problemStartDate;
    private String takingDrug;
    private Date visitDate=new Date();

    private VISIT_PROBLEM_CAUSE visitCause;

    private String otherVisitCause;

    private String disease;


    private PREGNANCY_STATUS pregnancyStatus;


    private YES_OR_NO surgicalStatus;
    private String surgicalType;


    private YES_OR_NO failStatus;
    private String failType;


    private YES_OR_NO fractureStatus;
    private String fractureType;


    private YES_OR_NO orthopedicMetalStatus;
    private String orthopedicMetalType;


    private YES_OR_NO diarrhea;


    private YES_OR_NO headAche;


    private Double bodyTemperature;
    private String pulseRate;
    private String respirationRate;
    private String bloodPressure;
    private Double weight;
    private Double height;

    private String vitalSignNote;
    private String visitNote;

    private PATIENT_VISIT_METHOD visitMethod;

    private String emergencyContactName;
    private String emergencyContactAddress;
    private String emergencyContactMobile;
    private String emergencyContactEmail;

    private Long departmentId;
    private Long examinerId;

    public static PatientVisit toPatientVisit(PatientVisitCreateDTO dto, Patient patient, Staff staff){
        PatientVisit visit = new PatientVisit();

        if(patient!=null ) {
            visit.setPatient(patient);
        }
        if(staff!=null ) {
            visit.setStaff(staff);
        }
        visit.setHealthProblem(dto.getHealthProblem());
        visit.setProblemStartDate(dto.getProblemStartDate());
        visit.setTakingDrug(dto.getTakingDrug());
        visit.setVisitDate(dto.getVisitDate());
        visit.setVisitCause(dto.getVisitCause());
        visit.setOtherVisitCause(dto.getOtherVisitCause());
        visit.setDisease(dto.getDisease());
        visit.setPregnancyStatus(dto.getPregnancyStatus());
        visit.setSurgicalStatus(dto.getSurgicalStatus());
        visit.setSurgicalType(dto.getSurgicalType());
        visit.setFailStatus(dto.getFailStatus());
        visit.setFailType(dto.getFailType());
        visit.setFractureStatus(dto.getFractureStatus());
        visit.setFractureType(dto.getFractureType());
        visit.setOrthopedicMetalType(dto.getOrthopedicMetalType());
        visit.setOrthopedicMetalStatus(dto.getOrthopedicMetalStatus());
        visit.setHeadAche(dto.getHeadAche());
        visit.setBodyTemperature(dto.getBodyTemperature());
        visit.setPulseRate(dto.getPulseRate());
        visit.setRespirationRate(dto.getRespirationRate());
        visit.setVisitMethod(dto.getVisitMethod());
        visit.setVitalSignNote(dto.getVitalSignNote());
        visit.setVisitNote(dto.getVisitNote());
        visit.setEmergencyContactAddress(dto.getEmergencyContactAddress());
        visit.setEmergencyContactEmail(dto.getEmergencyContactEmail());
        visit.setEmergencyContactMobile(dto.getEmergencyContactMobile());
        visit.setEmergencyContactName(dto.getEmergencyContactName());

        visit.setDiarrhea(dto.getDiarrhea());
        visit.setDepartment(dto.getDepartmentId());
        visit.setExaminer(dto.getExaminerId());

        visit.setExamined(false);

        return visit;
    }


}
