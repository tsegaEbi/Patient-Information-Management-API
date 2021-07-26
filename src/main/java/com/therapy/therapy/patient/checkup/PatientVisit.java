package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.common.*;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="patient_visit")
public class PatientVisit extends Model {

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="staff_id")
    private Staff staff;

    private String healthProblem;
    private Date problemStartDate;
    private String takingDrug;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitDate=new Date();

    @Enumerated(EnumType.STRING)
    private VISIT_PROBLEM_CAUSE visitCause;

    private String otherVisitCause;

    private String disease;

    @Enumerated(EnumType.STRING)
    private PREGNANCY_STATUS pregnancyStatus;

    @Enumerated(EnumType.STRING)
    private YES_OR_NO surgicalStatus;
    private String surgicalType;

    @Enumerated(EnumType.STRING)
    private YES_OR_NO failStatus;
    private String failType;

    @Enumerated(EnumType.STRING)
    private YES_OR_NO fractureStatus;
    private String fractureType;

    @Enumerated(EnumType.STRING)
    private YES_OR_NO orthopedicMetalStatus;
    private String orthopedicMetalType;

    @Enumerated(EnumType.STRING)
    private YES_OR_NO diarrhea;

    @Enumerated(EnumType.STRING)
    private YES_OR_NO headAche;

    // Vital Signs

    private Double bodyTemperature;
    private String pulseRate;
    private String respirationRate;
    private String bloodPressure;
    private Double weight;
    private Double height;

    private String vitalSignNote;
    private String visitNote;

    @Enumerated(EnumType.STRING)
    private PATIENT_VISIT_METHOD visitMethod;

   private String emergencyContactName;
   private String emergencyContactAddress;
   private String emergencyContactMobile;
   private String emergencyContactEmail;


   // Assignment

    private Long examiner;
    private Long department;
    private Boolean examined;

}
