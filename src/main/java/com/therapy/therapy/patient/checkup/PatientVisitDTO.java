package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.common.*;
import com.therapy.therapy.department.Department;
import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class PatientVisitDTO {
    private Long id;
    private Long patientId;
    private String patientFullName;
    private Date dob;
    private Sex sex;

    private Long staffId;
    private String staffFullName;



    private String healthProblem;
    private Date problemStartDate;
    private String takingDrug;
    private Date visitDate;

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
    private String pr;
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

    //
    private Boolean assigned;
    private Long examinerId;
    private String examinerName;
    private Boolean examined;
    private String assignedDepartment;


   //
    private String spo2;
    private String bmi;
    //
    private ExaminationDTO examination;

    public static PatientVisitDTO toPatientVisitDTO(PatientVisit  dto, Department dept, Staff examiner,ExaminationDTO exam){
        if(dto!=null) {

            PatientVisitDTO visit = new PatientVisitDTO();
            visit.setPr(dto.getPr());
            visit.setSpo2(dto.getSpo2());
            visit.setBmi(dto.getBmi());

            visit.setId(dto.getId());
            visit.setPatientId(dto.getPatient().getId());
            visit.setPatientFullName(dto.getPatient().getPrefix() + " " + dto.getPatient().getFirstName() + " " + dto.getPatient().getFatherName());
            visit.setDob(dto.getPatient().getDob());

            visit.setStaffId(dto.getStaff().getId());
            visit.setStaffFullName(dto.getStaff().getPrefix() + " " + dto.getStaff().getFirstName() + " " + dto.getStaff().getFatherName());

            visit.setHealthProblem(dto.getHealthProblem());
            visit.setProblemStartDate(dto.getProblemStartDate());
            visit.setTakingDrug(dto.getTakingDrug());
            visit.setVisitDate(dto.getDateCreated());
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
            visit.setPr(dto.getRespirationRate());
            visit.setHeight(dto.getHeight());
            visit.setWeight(dto.getWeight());

            visit.setVisitMethod(dto.getVisitMethod());
            visit.setVitalSignNote(dto.getVitalSignNote());
            visit.setVisitNote(dto.getVisitNote());
            visit.setEmergencyContactAddress(dto.getEmergencyContactAddress());
            visit.setEmergencyContactEmail(dto.getEmergencyContactEmail());
            visit.setEmergencyContactMobile(dto.getEmergencyContactMobile());
            visit.setEmergencyContactName(dto.getEmergencyContactName());

            visit.setDiarrhea(dto.getDiarrhea());
            visit.setSex(dto.getPatient().getSex());
            visit.setAssigned(false);
            visit.setExamined(dto.getExamined());

            if (dept != null) {
                visit.setAssignedDepartment(dept.getName());
                visit.setAssigned(true);
            }
            if(examiner!=null){
                visit.setExaminerId(examiner.getId());
                visit.setExaminerName(examiner.getPrefix()+" "+examiner.getFirstName()+" "+examiner.getFatherName());
                visit.setAssigned(true);
            }

            if(exam!=null){
                visit.setExamination(exam);
            }

            visit.setBmi(dto.getBmi());
            visit.setSpo2(dto.getSpo2());
            visit.setPr(dto.getPr());

            return visit;
        }
        return null;
    }
    public static PatientVisitDTO toDTOBasic(PatientVisit  dto, Department dept){
        if(dto!=null) {

            PatientVisitDTO visit = new PatientVisitDTO();

            visit.setId(dto.getId());
            visit.setPatientId(dto.getPatient().getId());
            visit.setPatientFullName(dto.getPatient().getPrefix() + " " + dto.getPatient().getFirstName() + " " + dto.getPatient().getFatherName());
            visit.setDob(dto.getPatient().getDob());

            visit.setStaffId(dto.getStaff().getId());
            visit.setStaffFullName(dto.getStaff().getPrefix() + " " + dto.getStaff().getFirstName() + " " + dto.getStaff().getFatherName());

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

            visit.setVisitMethod(dto.getVisitMethod());
            visit.setVitalSignNote(dto.getVitalSignNote());
            visit.setVisitNote(dto.getVisitNote());
            visit.setEmergencyContactAddress(dto.getEmergencyContactAddress());
            visit.setEmergencyContactEmail(dto.getEmergencyContactEmail());
            visit.setEmergencyContactMobile(dto.getEmergencyContactMobile());
            visit.setEmergencyContactName(dto.getEmergencyContactName());

            visit.setDiarrhea(dto.getDiarrhea());
            visit.setSex(dto.getPatient().getSex());
            visit.setAssigned(false);
            visit.setExamined(dto.getExamined());

            if (dept != null) {
                visit.setAssignedDepartment(dept.getName());
                visit.setAssigned(true);
            }

            visit.setPr(dto.getPr());
            visit.setSpo2(dto.getSpo2());
            visit.setBmi(dto.getBmi());

            return visit;
        }
        return null;
    }

    public static PatientVisitDTO toLabOrderDTO(PatientVisit dto){
        PatientVisitDTO visit = new PatientVisitDTO();
        visit.setId(dto.getId());
        visit.setPatientId(dto.getPatient().getId());
        visit.setPatientFullName(dto.getPatient().getPrefix() + " " + dto.getPatient().getFirstName() + " " + dto.getPatient().getFatherName());
        visit.setDob(dto.getPatient().getDob());

        visit.setStaffId(dto.getStaff().getId());
        visit.setStaffFullName(dto.getStaff().getPrefix() + " " + dto.getStaff().getFirstName() + " " + dto.getStaff().getFatherName());
        visit.setHealthProblem(dto.getHealthProblem());
        return visit;
    }
    public static PatientVisitDTO toProfileDTO(PatientVisit dto){
        PatientVisitDTO visit = new PatientVisitDTO();
        visit.setId(dto.getId());
        visit.setPatientId(dto.getPatient().getId());
        visit.setPatientFullName(dto.getPatient().getPrefix() + " " + dto.getPatient().getFirstName() + " " + dto.getPatient().getFatherName());
        visit.setDob(dto.getPatient().getDob());
        visit.setHealthProblem(dto.getHealthProblem());
        return visit;
    }
}
